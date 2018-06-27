package com.accentrix.hku.web.common;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "language")
@SessionScoped
public class InternationalizationBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Locale ENG = Locale.ENGLISH;
    private final static Locale CHI = Locale.SIMPLIFIED_CHINESE;
    public static Locale locale = ENG;

    public Locale getLocale() {
        return (locale);
    }

    public void setEnglish(ActionEvent event) {
        locale = ENG;
        updateViewLocale();
    }

    public void setChinese(ActionEvent event) {
        locale = CHI;
        updateViewLocale();
    }

    private void updateViewLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}