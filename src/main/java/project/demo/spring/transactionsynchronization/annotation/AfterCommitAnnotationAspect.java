package project.demo.spring.transactionsynchronization.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.demo.spring.transactionsynchronization.AfterCommitExecutor;

@Aspect
@Component
public class AfterCommitAnnotationAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfterCommitAnnotationAspect.class);
 
    private final AfterCommitExecutor afterCommitExecutor;
 
    @Autowired
    public AfterCommitAnnotationAspect(AfterCommitExecutor afterCommitExecutor) {
        this.afterCommitExecutor = afterCommitExecutor;
    }
 
    @Around(value = "@within(project.demo.spring.transactionsynchronization.annotation.AfterCommit) || " +
            "@annotation(project.demo.spring.transactionsynchronization.annotation.AfterCommit)", argNames = "pjp")
    public Object aroundAdvice(final ProceedingJoinPoint pjp) {
        afterCommitExecutor.execute(new PjpAfterCommitRunnable(pjp));
        return null;
    }
 
    private static final class PjpAfterCommitRunnable implements Runnable {
        private final ProceedingJoinPoint pjp;
 
        public PjpAfterCommitRunnable(ProceedingJoinPoint pjp) {
            this.pjp = pjp;
        }
 
        @Override
        public void run() {
            try {
                pjp.proceed();
            } catch (Throwable e) {
                LOGGER.error("Exception while invoking pjp.proceed()", e);
                throw new RuntimeException(e);
            }
        }
 
        @Override
        public String toString() {
            String typeName = pjp.getTarget().getClass().getSimpleName();
            String methodName = pjp.getSignature().getName();
            return "PjpAfterCommitRunnable[type=" + typeName + ", method=" + methodName + "]";
        }
    }
 
}