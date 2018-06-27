package com.accentrix.hku.concurrent;

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultUncaughtExceptionHandler implements UncaughtExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultUncaughtExceptionHandler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("Uncaught erro. Thread id:{} , Thread name:{} , Error message:{}", t.getId(), t.getName(),
                e.getMessage(), e);
    }

}
