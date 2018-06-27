package com.accentrix.hku.web.common;

import java.io.Serializable;

import com.accentrix.hku.common.enumeration.SearchMode;

public abstract class BaseSearchBean implements Serializable {

    private static final long serialVersionUID = 8695906579101167720L;

    protected SearchMode searchMode = SearchMode.Basic;
    protected boolean result;
    protected String basicSearchCriteria;

    public void switchSearchMode(SearchMode selectedMode) {
        searchMode = selectedMode;
    }

    public abstract void search();

    public abstract void reset();

    public abstract void edit(String id);

    public SearchMode getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getBasicSearchCriteria() {
        return basicSearchCriteria;
    }

    public void setBasicSearchCriteria(String basicSearchCriteria) {
        this.basicSearchCriteria = basicSearchCriteria;
    }

}
