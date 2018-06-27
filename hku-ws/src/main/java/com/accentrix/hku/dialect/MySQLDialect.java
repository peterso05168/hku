/*
 * $Id: MySQLDialect.java 10366 2017-06-28 08:21:47Z jyang $
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
