/*
 * $Id: MySQLDialect.java 872 2018-05-24 10:19:35Z Lonny Wei $
 * 
 * Copyright (c) 2001-2017 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.dialect;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.LongType;

public class MySQLDialect extends MySQL5InnoDBDialect {

    public MySQLDialect() {
        super();
        registerFunction("bitand", new SQLFunctionTemplate(LongType.INSTANCE, "(?1 & ?2)"));
    }

}
