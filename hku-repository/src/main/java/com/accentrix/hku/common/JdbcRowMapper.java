/*
 * $Id: JdbcRowMapper.java 21813 2016-12-22 09:50:45Z tony.zhao $
 * 
 * Copyright (c) 2001-2017 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

public class JdbcRowMapper<T> implements RowMapper<T> {
    private static final Logger LOG = LoggerFactory.getLogger(JdbcRowMapper.class);

    private Class<T> clazz;

    public JdbcRowMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {

        T t = null;
        try {
            Constructor<T> constructor = clazz.getConstructor();
            t = constructor.newInstance();
        } catch (SecurityException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            LOG.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage(), e);
        } catch (InstantiationException e) {
            LOG.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            LOG.error(e.getMessage(), e);
        }

        Field[] sonFields = clazz.getDeclaredFields();
        Field[] parentFields = (Field[]) clazz.getSuperclass().getDeclaredFields();
        Field[] fields = new Field[sonFields.length + parentFields.length];
        System.arraycopy(sonFields, 0, fields, 0, sonFields.length);
        System.arraycopy(parentFields, 0, fields, sonFields.length, parentFields.length);
        for (Field field : fields) {

            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if (field.getAnnotation(Transient.class) != null) {
                continue;
            }

            String columnName = getColumnName(field);

            Object value = getValue(rs, field.getType(), columnName);

            field.setAccessible(true);
            try {
                field.set(t, value);
            } catch (IllegalArgumentException e) {
                LOG.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return t;
    }

    private String getColumnName(Field field) {
        String columnName = null;

        Column column = field.getAnnotation(Column.class);
        if (column != null) {
            columnName = column.name();
        } else {
            columnName = field.getName();
        }

        return columnName;
    }

    private Object getValue(ResultSet rs, Class<?> FieldClazz, String columnName) throws SQLException {

        Object value = null;

        if (FieldClazz == Integer.class || FieldClazz == int.class) {
            value = rs.getInt(columnName);
        } else if (FieldClazz == Long.class || FieldClazz == long.class) {
            value = rs.getLong(columnName);
        } else if (FieldClazz == Float.class || FieldClazz == float.class) {
            value = rs.getFloat(columnName);
        } else if (FieldClazz == Double.class || FieldClazz == double.class) {
            value = rs.getDouble(columnName);
        } else if (FieldClazz == String.class) {
            value = rs.getString(columnName);
        } else if (FieldClazz == Date.class) {
            value = rs.getDate(columnName);
        } else if (FieldClazz == Boolean.class || FieldClazz == boolean.class) {
            value = rs.getBoolean(columnName);
        } else if (FieldClazz == Byte.class || FieldClazz == byte.class) {
            value = rs.getByte(columnName);
        } else if (FieldClazz == BigDecimal.class) {
            value = rs.getBigDecimal(columnName);
        } else {
            value = rs.getObject(columnName);
        }

        return value;
    }

}
