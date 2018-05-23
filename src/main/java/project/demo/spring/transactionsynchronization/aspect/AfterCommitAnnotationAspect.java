package project.demo.spring.transactionsynchronization.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.demo.spring.transactionsynchronization.executor.CommitExecutor;
import project.demo.spring.transactionsynchronization.inner.PjpCommitRunnable;

@Aspect
@Component
public class AfterCommitAnnotationAspect {

    @Autowired
    private CommitExecutor afterCommitExecutor;

    @Around(value = "@within(project.demo.spring.transactionsynchronization.annotation.AfterCommit) || " +
            "@annotation(project.demo.spring.transactionsynchronization.annotation.AfterCommit)", argNames = "pjp")
    public Object aroundAdvice(final ProceedingJoinPoint pjp) {
        afterCommitExecutor.execute(new PjpCommitRunnable(pjp));
        return null;
    }
}
