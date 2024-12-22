package com.example.domainredis.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class AopNewTransaction {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {

        Object transactionId = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info("AopNewTransaction transactionId {}", transactionId.toString());

        return joinPoint.proceed();
    }
}
