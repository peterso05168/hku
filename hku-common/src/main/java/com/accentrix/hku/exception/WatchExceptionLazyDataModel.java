package com.accentrix.hku.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.exception.annotation.WatchException;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;

public abstract class WatchExceptionLazyDataModel<T> extends LazyDataModel<T> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(WatchExceptionLazyDataModel.class);

    public static final String SESSION_KEY_WATCH_EXCEPTION = "WatchExceptionLazyDataModel_watchException";
    public static final String SESSION_KEY_THROWABLE = "WatchExceptionLazyDataModel_throwable";

    private String exceptionExecuteScript = "$('.watchExceptionDataModel_submmitBtn').click()";

    public WatchExceptionLazyDataModel() {
    }

    public WatchExceptionLazyDataModel(String exceptionExecuteScript) {
        this.exceptionExecuteScript = exceptionExecuteScript;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            return doLoad(first, pageSize, sortField, sortOrder, filters);
        } catch (Exception throwable) {
            LOG.error(throwable.getMessage(), throwable);
            WatchException watchException = this.getClass().getAnnotation(WatchException.class);
            JSFUtil.getSessionMap().put(SESSION_KEY_WATCH_EXCEPTION, watchException);
            JSFUtil.getSessionMap().put(SESSION_KEY_THROWABLE, throwable);
            UIUtil.execute(exceptionExecuteScript);
            return Collections.emptyList();
        }
    }

    protected abstract List<T> doLoad(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters);
}
