package project.demo.spring.transactionsynchronization.executor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AfterCommitExecutorDefaultImpl extends CommitExecutorBaseImpl {

    private static final Logger logger = LoggerFactory.getLogger(AfterCommitExecutorDefaultImpl.class);

    /**
     * 事务成功后调用
     */
    @Override
    public void afterCommit() {
        List<Runnable> threadRunnables = RUNNABLES.get();

        logger.debug("Transaction successfully committed, executing {} runnables", threadRunnables.size());

        for (Runnable runnable : threadRunnables) {
            logger.debug("Executing runnable {}", runnable);
            try {
                runnable.run();
            } catch (RuntimeException e) {
                logger.error("Failed to execute runnable " + runnable, e);
            }
        }
    }

    @Override
    public void afterCompletion(int status) {
        logger.debug("Transaction completed with status {}", status == STATUS_COMMITTED ? "COMMITTED" : "ROLLED_BACK");
        RUNNABLES.remove();
    }
}
