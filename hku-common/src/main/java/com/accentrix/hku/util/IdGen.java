/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.accentrix.hku.util;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

public class IdGen implements SessionIdGenerator {

    @Override
    public Serializable generateId(Session session) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
