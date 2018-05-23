package project.demo.spring.transactionsynchronization.inner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PjpCommitRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(PjpCommitRunnable.class);

    private final ProceedingJoinPoint pjp;

    public PjpCommitRunnable(ProceedingJoinPoint pjp) {
        this.pjp = pjp;
    }

    @Override
    public void run() {
        try {
            pjp.proceed();
        } catch (Throwable e) {
            logger.error("Exception while invoking pjp.proceed()", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        String typeName = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        return "PjpCommitRunnable[type=" + typeName + ", method=" + methodName + "]";
    }
}
