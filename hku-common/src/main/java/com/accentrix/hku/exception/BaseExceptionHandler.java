package com.accentrix.hku.exception;

import java.lang.reflect.UndeclaredThrowableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.exception.annotation.WatchException;
import com.accentrix.hku.exception.annotation.WatchException4Popup;
import com.accentrix.hku.exception.handler.ExceptionHandler;
import com.accentrix.hku.exception.handler.ExceptionHandler4Popup;

public class BaseExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(BaseExceptionHandler.class);

    public static void handleException(final WatchException watchException,
            final WatchException4Popup watchException4Popup, final Throwable thr) {
        LOG.debug(thr.getMessage(), thr);

        Throwable ex = thr;

        if (ex instanceof UndeclaredThrowableException) {
            UndeclaredThrowableException undeclaredThr = (UndeclaredThrowableException) ex;
            ex = undeclaredThr.getUndeclaredThrowable();
            LOG.debug(ex.getMessage(), ex);
        }

        if (watchException == null && watchException4Popup == null) {
            LOG.debug("both watchException & watchException4Popup are null");
            return;
        }

        if (watchException != null) {
            handleWatchException(watchException, ex);
        }

        if (watchException4Popup != null) {
            handleWatchException4Popup(watchException4Popup, ex);
        }

        return;
    }

    private static void handleWatchException(final WatchException watchException, Throwable ex) {
        Class<? extends ExceptionHandler>[] clazz = watchException.exceptionHandler();
        String message = watchException.message();
        String messageKeyPrefix = watchException.messageKeyPrefix();
        String[] update = watchException.update();

        MatchType matchType = watchException.matchType();
        String[] specifiedClassNames = watchException.specifiedClassName();

        String exClassName = getExactExClassName(ex);

        boolean needHandle = checkNeedHandle(matchType, specifiedClassNames, exClassName);

        if (!needHandle) {
            LOG.debug("needHandle: {}", needHandle);
            return;
        }

        for (Class<? extends ExceptionHandler> c : clazz) {
            try {
                ExceptionHandler handler = c.newInstance();
                LOG.debug("handling at {}", handler.getClass().getName());
                handler.handle(ex, update, message, messageKeyPrefix);
            } catch (InstantiationException e) {
                LOG.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private static void handleWatchException4Popup(WatchException4Popup watchException4Popup, Throwable ex) {
        Class<? extends ExceptionHandler4Popup>[] clazz = watchException4Popup.exceptionHandler();
        String message = watchException4Popup.message();
        String messageKeyPrefix = watchException4Popup.messageKeyPrefix();
        String popup = watchException4Popup.popup();

        MatchType matchType = watchException4Popup.matchType();
        String[] specifiedClassNames = watchException4Popup.specifiedClassName();

        String exClassName = getExactExClassName(ex);

        boolean needHandle = checkNeedHandle(matchType, specifiedClassNames, exClassName);

        if (!needHandle) {
            LOG.debug("needHandle: {}", needHandle);
            return;
        }

        for (Class<? extends ExceptionHandler4Popup> c : clazz) {
            try {
                ExceptionHandler4Popup handler = c.newInstance();
                LOG.debug("handling at {}", handler.getClass().getName());
                handler.handle(ex, popup, message, messageKeyPrefix);
            } catch (InstantiationException e) {
                LOG.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private static boolean checkNeedHandle(MatchType matchType, String[] specifiedClassNames, String exClassName) {
        LOG.debug("matchType: {}", matchType);
        LOG.debug("ex class: {}", exClassName);
        if (LOG.isDebugEnabled() && specifiedClassNames != null) {
            for (String c : specifiedClassNames) {
                LOG.debug("specifiedClassNames: {}", c);
            }
        }

        if (matchType == MatchType.ALL) {
            return true;
        }

        if (matchType == MatchType.SPECIFIED_ONLY) {
            if (specifiedClassNames == null || specifiedClassNames.length == 0) {
                LOG.debug("specifiedClassNames is null");
                return false;
            }

            for (String classname : specifiedClassNames) {
                if (classname.equals(exClassName)) {
                    return true;
                }
            }

            return false;
        }

        if (matchType == MatchType.SPECIFIED_EXCEPT) {
            if (specifiedClassNames == null || specifiedClassNames.length == 0) {
                return true;
            }

            boolean oneOfSpecified = false;

            for (String classname : specifiedClassNames) {
                if (classname.equals(exClassName)) {
                    oneOfSpecified = true;
                }
            }

            return !oneOfSpecified;
        }

        LOG.warn("should not go here!");
        return false;
    }

    private static String getExactExClassName(Throwable ex) {
        String exClassName = ex.getClass().getName();
        if (ex instanceof WsRuntimeException) {
            LOG.debug("it is WsRuntimeException");
            WsRuntimeException wsEx = (WsRuntimeException) ex;
            exClassName = wsEx.getCauseClassName();
        }
        return exClassName;
    }
}
