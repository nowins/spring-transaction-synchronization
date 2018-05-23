package project.demo.spring.transactionsynchronization.executor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;
import project.demo.spring.transactionsynchronization.executor.CommitExecutor;

import java.util.ArrayList;
import java.util.List;

public class CommitExecutorBaseImpl extends TransactionSynchronizationAdapter implements CommitExecutor {

    private static final Logger logger = LoggerFactory.getLogger(CommitExecutorBaseImpl.class);

    static final ThreadLocal<List<Runnable>> RUNNABLES = new ThreadLocal<>();

    @Override
    public void execute(Runnable runnable) {
        Assert.notNull(runnable, "Runnable must not be null");

        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            logger.info("Transaction synchronization is NOT ACTIVE. Executing right now runnable {}", runnable);
            runnable.run();
            return;
        }

        List<Runnable> threadRunnables = RUNNABLES.get();
        if (null == threadRunnables) {
            threadRunnables = new ArrayList<>();
            RUNNABLES.set(threadRunnables);
            TransactionSynchronizationManager.registerSynchronization(this);
        }

        threadRunnables.add(runnable);
    }
}
