package com.accentrix.hku.web.system;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.accentrix.hku.util.filter.CheckTokenFilter;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月9日 下午4:41:57
 * @version 1.0
 */

@ManagedBean
@ApplicationScoped
public class IndexBean {
    public long getUserExp() {
        Long exp = CheckTokenFilter.USER_EXP.get();
        if (exp == null) {
            exp = 0L;
        }
        return exp;
    }
}
