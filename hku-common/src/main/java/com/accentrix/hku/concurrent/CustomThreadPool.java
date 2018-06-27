package com.accentrix.hku.concurrent;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomThreadPool extends ThreadPoolExecutor {
    private static final Logger log = LoggerFactory.getLogger(CustomThreadPool.class);
    private ThreadPoolStatus status = ThreadPoolStatus.shutdowned;
    private AtomicInteger totalTaskCount = new AtomicInteger();

    public CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        status = ThreadPoolStatus.running;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        log.debug("beforeExecute()... Thread id:{} , name:{} , priority:{}", t.getId(), t.getName(), t.getPriority());
        super.beforeExecute(t, r);
        totalTaskCount.incrementAndGet();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        totalTaskCount.decrementAndGet();
    }

    @Override
    public void shutdown() {
        super.shutdown();
        status = ThreadPoolStatus.shutdowned;
    }

    @Override
    public List<Runnable> shutdownNow() {
        status = ThreadPoolStatus.shutdowned;
        return super.shutdownNow();
    }

    public ThreadPoolStatus getStatus() {
        return status;
    }

    public AtomicInteger getTotalTaskCount() {
        return totalTaskCount;
    }

    public enum ThreadPoolStatus {
        running, shutdowned, paused;
    }
}
