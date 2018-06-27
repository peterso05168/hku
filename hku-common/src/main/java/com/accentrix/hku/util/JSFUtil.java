package com.accentrix.hku.util;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSFUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JSFUtil.class);

    private JSFUtil() {
    }

    public static <T> T getCurrentInstance(String name, Class<T> clazz) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null) {
            LOG.warn("FacesContext.getCurrentInstance() is null, is it for unit test?");
            return null;
        }

        String expression = "#{" + name + "}";
        return context.getApplication().evaluateExpressionGet(context, expression, clazz);
    }

    public static Object getCurrentInstance(String name) {
        return getCurrentInstance(name, Object.class);
    }

    public static Map<String, Object> getSessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

}
