package com.example.domainredis.aop;

import com.example.domainredis.annotation.DistributedLock;
import com.example.portfoliocore.util.CustomSpringELParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
@Order(0)
@Aspect
public class DistributedLockAspect {

    private static final String REDISSON_LOCK_PREFIX = "LOCK:";

    private final RedissonClient redissonClient;

    private final AopNewTransaction aopNewTransaction;

    @Around("@annotation(com.example.domainredis.annotation.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("DistributedLockAspect ");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.info("getParameterNames: {}, getArgs: {}", signature.getParameterNames(), joinPoint.getArgs());

        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);


        String key = REDISSON_LOCK_PREFIX + CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), distributedLock.key());
        RLock rLock = redissonClient.getLock(key);
        log.info("DistributedLockAspect:lock key: {}, waitTime: {}", key, distributedLock.waitTime());

        try {
            boolean available = rLock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());

            if (!available) {
                return false;
            }
            log.info("{} : Redisson Lock 진입 : {} {}", Thread.currentThread().getId(), method.getName(), key);

            return aopNewTransaction.proceed(joinPoint);

        } catch (InterruptedException e) {
            throw new InterruptedException("Failed to acquire lock: " + key);
        } finally {
            log.info("{} : Redisson Lock 해제 : {} {}", Thread.currentThread().getId(), method.getName(), key);
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }

    }
}
