package com.accentrix.hku.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.concurrent.CustomThreadPool.ThreadPoolStatus;

public enum ThreadPoolManager {
    INSTANCE;

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolManager.class);
    private final Lock startupThreadPoolLock = new ReentrantLock();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();
    private int corePoolSize = 10;
    private int maximumPoolSize = 50;
    private long keepAliveTime = 10;
    private int bufferWorkQueueSize = Integer.MAX_VALUE;
    private TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private RejectedExecutionHandler rejectedExecutionHandler = new AbortPolicy();
    private ThreadFactory threadFactory = new DefaultThreadFactory();
    private volatile boolean isStarted = false;
    private volatile CustomThreadPool threadPool;

    private CustomThreadPool startAndGetThreadPool() {
        if (threadPool != null) {
            return threadPool;
        }
        startupThreadPoolLock.lock();
        LOG.info("Thread pool is starting up");
        try {
            if (threadPool == null) {
                BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>(bufferWorkQueueSize);
                threadPool = new CustomThreadPool(corePoolSize, maximumPoolSize, keepAliveTime, TIME_UNIT, workQueue,
                        Executors.defaultThreadFactory(), rejectedExecutionHandler);

                LOG.info("Thread Pool started [" + "Core Pool Size=" + threadPool.getCorePoolSize()
                        + ", Maximum Pool Size=" + threadPool.getMaximumPoolSize() + ", Keep Alive Time="
                        + threadPool.getKeepAliveTime(TIME_UNIT) + " " + TIME_UNIT.toString().toLowerCase() + "]"
                        + ", Work Queue Size=" + threadPool.getQueue().size());
                isStarted = true;
            }
            LOG.info("Thread pool started successfully");
            return threadPool;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            startupThreadPoolLock.unlock();
        }
    }

    public void shutDownThreadPool() throws InterruptedException {
        LOG.info("Thread Pool is stopping...");
        CustomThreadPool threadPool = startAndGetThreadPool();
        if (threadPool != null) {
            threadPool.shutdown();
            threadPool.awaitTermination(10, TIME_UNIT);
            isStarted = false;
            LOG.info("Thread Pool has been stopped already");
        }
    }

    public ThreadPoolStatus getThreadPoolStatus() {
        CustomThreadPool threadPool = startAndGetThreadPool();
        return threadPool.getStatus();
    }

    public boolean isStarted() {
        return isStarted;
    }

    public <V> Future<V> submitTransactional(TransactionalCallable<V> callable) {
        CustomThreadPool threadPool = startAndGetThreadPool();
        return threadPool.submit(callable);
    }

    public <V> Future<V> submit(Callable<V> callable) {
        CustomThreadPool threadPool = startAndGetThreadPool();
        return threadPool.submit(callable);
    }

    public void execute(Runnable runnable) {
        CustomThreadPool threadPool = startAndGetThreadPool();
        threadPool.execute(runnable);
    }

    public void executeTransactional(TransactionalRunnable runnable) {
        CustomThreadPool threadPool = startAndGetThreadPool();
        threadPool.execute(runnable);
    }

    public int getTotalTaskCount() {
        return startAndGetThreadPool().getTotalTaskCount().get();
    }

    public int getCorePoolSize() {
        readLock.lock();
        try {
            return corePoolSize;
        } finally {
            readLock.unlock();
        }
    }

    public void setCorePoolSize(int corePoolSize) {
        writeLock.lock();
        try {
            this.corePoolSize = corePoolSize;
        } finally {
            writeLock.unlock();
        }
    }

    public int getMaximumPoolSize() {
        readLock.lock();
        try {
            return maximumPoolSize;
        } finally {
            readLock.unlock();
        }
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        writeLock.lock();
        try {
            this.maximumPoolSize = maximumPoolSize;
        } finally {
            writeLock.unlock();
        }
    }

    public long getKeepAliveTime() {
        readLock.lock();
        try {
            return keepAliveTime;
        } finally {
            readLock.unlock();
        }
    }

    public void setKeepAliveTime(long keepAliveTime) {
        writeLock.lock();
        try {
            this.keepAliveTime = keepAliveTime;
        } finally {
            writeLock.unlock();
        }
    }

    public int getBufferWorkQueueSize() {
        readLock.lock();
        try {
            return bufferWorkQueueSize;
        } finally {
            readLock.unlock();
        }
    }

    public void setBufferWorkQueueSize(int bufferWorkQueueSize) {
        writeLock.lock();
        try {
            this.bufferWorkQueueSize = bufferWorkQueueSize;
        } finally {
            writeLock.unlock();
        }
    }

    public TimeUnit getTIME_UNIT() {
        readLock.lock();
        try {
            return TIME_UNIT;
        } finally {
            readLock.unlock();
        }
    }

    public void setTIME_UNIT(TimeUnit tIME_UNIT) {
        writeLock.lock();
        try {
            TIME_UNIT = tIME_UNIT;
        } finally {
            writeLock.unlock();
        }
    }

    public RejectedExecutionHandler getRejectedExecutionHandler() {
        readLock.lock();
        try {
            return rejectedExecutionHandler;
        } finally {
            readLock.unlock();
        }
    }

    public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        writeLock.lock();
        try {
            this.rejectedExecutionHandler = rejectedExecutionHandler;
        } finally {
            writeLock.unlock();
        }
    }

    public ThreadFactory getThreadFactory() {
        readLock.lock();
        try {
            return threadFactory;
        } finally {
            readLock.unlock();
        }
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        writeLock.lock();
        try {
            this.threadFactory = threadFactory;
        } finally {
            writeLock.unlock();
        }
    }

    public ThreadPoolExecutor getThreadPool() {
        return startAndGetThreadPool();
    }
}
