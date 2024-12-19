package com.example.domainredis.aop;

import com.example.domainredis.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
@Aspect
public class DistributedLockAspect {

    private static final String REDISSON_LOCK_PREFIX = "LOCK:";

    private final RedissonClient redissonClient;

    @Around("@annotation(com.example.domainredis.annotation.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        DistributedLock lock = method.getAnnotation(DistributedLock.class);

        log.info("key: {}, waitTime: {}", lock.key(), lock.waitTime());

        //String key = REDISSON_LOCK_PREFIX + CustomSpringElParser.
        return joinPoint.proceed();
    }

}
