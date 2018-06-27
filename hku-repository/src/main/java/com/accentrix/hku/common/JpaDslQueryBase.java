/*
 * $Id: JpaDslQueryBase.java 25423 2017-11-23 04:38:30Z jyang $
 * 
 * Copyright (c) 2001-2008 Accentrix Company Limited. All Rights Reserved.
 * 
 * Accentrix Company Limited. ("Accentrix") retains copyright on all text, source
 * and binary code contained in this software and documentation. Accentrix grants
 * Licensee a limited license to use this software, provided that this copyright
 * notice and license appear on all copies of the software. The software source
 * code is provided for reference purposes only and may not be copied, modified 
 * or distributed.
 * 
 * THIS SOFTWARE AND DOCUMENTATION ARE PROVIDED "AS IS," WITHOUT ANY WARRANTY OF
 * ANY KIND UNLESS A SEPARATE WARRANTIES IS PURCHASED FROM ACCENTRIX AND REMAINS
 * VALID.  ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. ACCENTRIX SHALL NOT BE LIABLE
 * FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING OR MODIFYING THE
 * SOFTWARE OR ITS DERIVATIVES.
 * 
 * IN NO EVENT WILL ACCENTRIX BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE
 * USE OF OR INABILITY TO USE SOFTWARE, EVEN IF ACCENTRIX HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 */
package com.accentrix.hku.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.Assert;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;

public abstract class JpaDslQueryBase<T, Q extends EntityPathBase<T>> {

    private static final Logger LOG = LoggerFactory.getLogger(JpaDslQuery.class);

    public static final ThreadLocal<BooleanBuilder> BOOLEAN_BUILDERS = new ThreadLocal<BooleanBuilder>();
    public static final ThreadLocal<List<OrderSpecifier<?>>> ORDERS = new ThreadLocal<List<OrderSpecifier<?>>>();

    public static final ThreadLocal<List<EntityPath<?>>> JOIN_FETCH_CLASSES = new ThreadLocal<List<EntityPath<?>>>();
    public static final ThreadLocal<List<EntityPath<?>>> LEFT_JOIN_FETCH_CLASSES = new ThreadLocal<List<EntityPath<?>>>();

    protected Class<T> entityClass;
    protected Class<Q> qClass;
    protected Q $;

    @SuppressWarnings("unchecked")
    public JpaDslQueryBase() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass(), 0);

        this.qClass = ReflectionUtils.getSuperClassGenricType(getClass(), 1);

        try {
            Constructor<Q> constructor = qClass.getConstructor(String.class);
            $ = constructor.newInstance(qClass.getSimpleName());
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
    }

    protected abstract EntityManager getEntityManager();

    protected JPAQuery<T> createJPAQuery() {
        JPAQuery<T> query = new JPAQuery<T>(getEntityManager());
        return query;
    }

    protected void eq(BooleanBuilder builder, StringPath stringPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            builder.and(stringPath.eq(value));
        }
    }

    protected void contains(BooleanBuilder builder, StringPath stringPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            builder.and(stringPath.contains(value));
        }
    }

    protected void in(BooleanBuilder builder, StringPath stringPath, Collection<String> values) {
        if (values != null && !values.isEmpty()) {
            if (values.size() == 1) {
                builder.and(stringPath.eq(values.iterator().next()));
            } else {
                builder.and(stringPath.in(values));
            }
        }
    }

    // ---------------------------------------------------------------------------------------------

    protected BooleanBuilder getCurrentBooleanBuilder() {
        BooleanBuilder builder = BOOLEAN_BUILDERS.get();
        if (builder == null) {
            builder = new BooleanBuilder();
            BOOLEAN_BUILDERS.set(builder);
        }
        return builder;
    }

    private BooleanBuilder getAndRemoveCurrentBooleanBuilder() {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        removeCurrentBooleanBuilder();
        return builder;
    }

    public void removeCurrentBooleanBuilder() {
        BOOLEAN_BUILDERS.remove();
    }

    private List<OrderSpecifier<?>> getCurrentOrder() {
        return ORDERS.get();
    }

    public void removeCurrentOrder() {
        ORDERS.remove();
    }

    /**
     * 
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>List<OrderSpecifier<?>></code> in ThreadLocal.
     * 
     * <li>{@link #list()}</li> <li>{@link #list(long offset, long limit)}</li>
     * 
     * @param order
     */
    protected void addOrder(OrderSpecifier<?> order) {
        if (order == null) {
            return;
        }

        List<OrderSpecifier<?>> orders = getCurrentOrder();
        if (orders == null) {
            orders = new ArrayList<OrderSpecifier<?>>();
            ORDERS.set(orders);
        }

        orders.add(order);
    }

    /**
     * 
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>List<OrderSpecifier<?>></code> in ThreadLocal.
     * 
     * <li>{@link #list()}</li> <li>{@link #list(long offset, long limit)}</li>
     * 
     * @param order
     */
    protected <RT extends Comparable<RT>> void orderBy(Expression<RT> target, com.querydsl.core.types.Order order) {
        OrderSpecifier<RT> os = new OrderSpecifier<RT>(order, target);

        addOrder(os);
    }

    /**
     * 
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>List<OrderSpecifier<?>></code> in ThreadLocal.
     * 
     * <li>{@link #list()}</li> <li>{@link #list(long offset, long limit)}</li>
     * 
     * @param order
     */
    protected <RT extends Comparable<RT>> void orderBy(Expression<RT> target, Direction direction) {
        orderBy(target, com.querydsl.core.types.Order.valueOf(direction.name()));
    }

    /**
     * 
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>List<OrderSpecifier<?>></code> in ThreadLocal.
     * 
     * <li>{@link #list()}</li> <li>{@link #list(long offset, long limit)}</li>
     * 
     * @param order
     */
    protected <RT extends Comparable<RT>> void orderBy(Expression<RT> target) {
        orderBy(target, com.querydsl.core.types.Order.ASC);
    }

    private List<EntityPath<?>> getCurrentJoinFetchClasses() {
        return JOIN_FETCH_CLASSES.get();
    }

    public void removeCurrentJoinFetchClasses() {
        JOIN_FETCH_CLASSES.remove();
    }

    private List<EntityPath<?>> getCurrentLeftJoinFetchClasses() {
        return LEFT_JOIN_FETCH_CLASSES.get();
    }

    public void removeCurrentLeftJoinFetchClasses() {
        LEFT_JOIN_FETCH_CLASSES.remove();
    }

    /**
     * 
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>List<Class<?>></code> in ThreadLocal.
     * 
     * 
     * @param clazz
     */
    protected void joinFetch(EntityPath<?> clazz) {
        if (clazz == null) {
            return;
        }

        List<EntityPath<?>> classes = getCurrentJoinFetchClasses();
        if (classes == null) {
            classes = new ArrayList<EntityPath<?>>();
            LEFT_JOIN_FETCH_CLASSES.set(classes);
        }

        classes.add(clazz);
    }

    /**
     * 
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>List<Class<?>></code> in ThreadLocal.
     * 
     * 
     * @param clazz
     */
    protected void leftJoinFetch(EntityPath<?> clazz) {
        if (clazz == null) {
            return;
        }

        List<EntityPath<?>> classes = getCurrentLeftJoinFetchClasses();
        if (classes == null) {
            classes = new ArrayList<EntityPath<?>>();
            LEFT_JOIN_FETCH_CLASSES.set(classes);
        }

        classes.add(clazz);
    }

    protected void eq(StringPath stringPath, String value) {

        if (StringUtils.isNotBlank(value)) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(stringPath.eq(value));
        }
    }

    protected void eq(BooleanPath booleanPath, Boolean value) {

        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(booleanPath.eq(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected <N extends Number & Comparable<?>> void eq(NumberPath<N> numberPath, N value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(numberPath.eq(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected <E extends Enum<E>> void eq(EnumPath<E> enumPath, E value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(enumPath.eq(value));
        }
    }

    protected <E extends Enum<E>> void ne(EnumPath<E> enumPath, E value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(enumPath.ne(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected <N extends Number & Comparable<?>> void gt(NumberPath<N> numberPath, N value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(numberPath.gt(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected void gt(DateTimePath<Date> dateTimePath, Date value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(dateTimePath.gt(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected void goe(DateTimePath<Date> dateTimePath, Date value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(dateTimePath.goe(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected void eq(DateTimePath<Date> dateTimePath, Date value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(dateTimePath.eq(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected void lt(DateTimePath<Date> dateTimePath, Date value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(dateTimePath.lt(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected void loe(DateTimePath<Date> dateTimePath, Date value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(dateTimePath.loe(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected <N extends Number & Comparable<?>> void lt(NumberPath<N> numberPath, N value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(numberPath.lt(value));
        }
    }

    protected <C extends Comparable<?>> void between(DateTimePath<C> drp, C from, C to) {
        if (from != null && to != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(drp.between(from, to));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected <N extends Number & Comparable<?>> void goe(NumberPath<N> numberPath, N value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(numberPath.goe(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param numberPath
     * @param value
     */
    protected <N extends Number & Comparable<?>> void loe(NumberPath<N> numberPath, N value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(numberPath.loe(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param stringPath
     * @param value
     */
    protected void contains(StringPath stringPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(stringPath.contains(value));
        }
    }

    protected <N extends Number & Comparable<?>> void contains(NumberPath<N> numberPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(numberPath.stringValue().contains(value));
        }
    }

    protected <E extends Enum<E>> void contains(EnumPath<E> enumPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(enumPath.stringValue().contains(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param stringPath
     * @param value
     */
    protected void startsWith(StringPath stringPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(stringPath.startsWith(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param stringPath
     * @param value
     */
    protected void endsWith(StringPath stringPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(stringPath.endsWith(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param stringPath
     * @param value
     */
    protected void orContains(StringPath stringPath1, StringPath stringPath2, String value) {
        if (StringUtils.isNotBlank(value)) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(stringPath1.contains(value).or(stringPath2.contains(value)));
        }
    }

    protected void any(StringPath stringPath, List<String> values) {
        BooleanExpression booleanExpression = null;
        if (values == null) {
            return;
        }

        if (!values.isEmpty()) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            if (values.size() == 1) {
                builder.and(stringPath.eq(values.get(0)));
            } else {
                for (int i = 0; i < values.size(); i++) {
                    if (i != 0) {
                        booleanExpression = booleanExpression.or(stringPath.eq(values.get(i)));
                    } else {
                        booleanExpression = stringPath.eq(values.get(0));
                    }
                }
                builder.and(booleanExpression);
            }
        }
    }

    protected <E extends Enum<E>> void any(EnumPath<E> enumPath, List<E> values) {
        BooleanExpression booleanExpression = null;
        if (values == null) {
            return;
        }

        if (!values.isEmpty()) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            if (values.size() == 1) {
                builder.and(enumPath.eq(values.get(0)));
            } else {
                for (int i = 0; i < values.size(); i++) {
                    if (i != 0) {
                        booleanExpression = booleanExpression.or(enumPath.eq(values.get(i)));
                    } else {
                        booleanExpression = enumPath.eq(values.get(0));
                    }
                }
                builder.and(booleanExpression);
            }
        }
    }

    protected <N extends Number & Comparable<?>> void orEq(NumberPath<N> numberPath1, NumberPath<N> numberPath2,
            N value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(numberPath1.eq(value).or(numberPath2.eq(value)));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param stringPath
     * @param value
     */
    protected void concat(StringPath stringPath1, StringPath stringPath2, String value) {
        if (StringUtils.isNotBlank(value)) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(stringPath1.concat(" ").concat(stringPath2).like(value));
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param stringPath
     * @param values
     */
    protected void in(StringPath stringPath, Collection<String> values) {
        if (values != null && !values.isEmpty()) {
            BooleanBuilder builder = getCurrentBooleanBuilder();

            if (values.size() == 1) {
                builder.and(stringPath.eq(values.iterator().next()));
            } else {
                builder.and(stringPath.in(values));
            }
        }
    }

    protected <E extends Enum<E>> void in(EnumPath<E> enumPath, Collection<E> values) {
        if (values != null && !values.isEmpty()) {
            BooleanBuilder builder = getCurrentBooleanBuilder();

            if (values.size() == 1) {
                builder.and(enumPath.eq(values.iterator().next()));
            } else {
                builder.and(enumPath.in(values));
            }
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param stringPath
     * @param values
     */
    protected <N extends Number & Comparable<?>> void in(NumberPath<N> numberPath, Collection<N> values) {
        if (values != null && !values.isEmpty()) {
            BooleanBuilder builder = getCurrentBooleanBuilder();

            if (values.size() == 1) {
                builder.and(numberPath.eq(values.iterator().next()));
            } else {
                builder.and(numberPath.in(values));
            }
        }
    }

    /**
     * if calling this method, you MUST call one of the following methods to
     * remove the <code>BooleanBuilder</code> in ThreadLocal.
     * 
     * <li>{@link #count()}</li> <li>{@link #list()}</li> <li>
     * {@link #list(long offset, long limit)}</li> <li>{@link #unique()}</li>
     * 
     * @param stringPath
     * @param values
     */
    protected void ne(StringPath stringPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(stringPath.notEqualsIgnoreCase(value));
        }
    }

    protected <N extends Number & Comparable<?>> void ne(NumberPath<N> numberPath, N value) {
        if (value != null) {
            BooleanBuilder builder = getCurrentBooleanBuilder();
            builder.and(numberPath.notIn(value));
        }
    }

    protected void isBlank(StringPath stringPath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(stringPath.isNull().or(stringPath.isEmpty()));
    }

    protected <E extends Enum<E>> void isNull(EnumPath<E> enumPath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(enumPath.isNull());
    }

    protected void isNull(StringPath stringPath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(stringPath.isNull());
    }

    protected void isNull(DateTimePath<Date> dateTimePath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(dateTimePath.isNull());
    }

    protected void isEmpty(StringPath stringPath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(stringPath.isEmpty());
    }

    protected <N extends Number & Comparable<?>> void isNull(NumberPath<N> numberPath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(numberPath.isNull());
    }

    protected <N extends Number & Comparable<?>> void isNotNull(NumberPath<N> numberPath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(numberPath.isNotNull());
    }

    protected void isNotNull(StringPath stringPath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(stringPath.isNotNull());
    }

    protected <E extends Enum<E>> void isNotNull(EnumPath<E> enumPath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(enumPath.isNotNull());
    }

    protected void isNotNull(DateTimePath<Date> dateTimePath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(dateTimePath.isNotNull());
    }

    protected void notBlank(StringPath stringPath) {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        builder.and(stringPath.isNotNull().and(stringPath.isNotEmpty()));
    }

    protected long count() {
        BooleanBuilder builder = getAndRemoveCurrentBooleanBuilder();
        return createJPAQuery().from($).where(builder).fetchCount();
    }

    protected <N extends Number & Comparable<?>> N sum(NumberPath<N> numberPath) {
        BooleanBuilder builder = getAndRemoveCurrentBooleanBuilder();
        return createJPAQuery().select(numberPath.sum()).from($).where(builder).fetchOne();
    }

    protected <N extends Number & Comparable<?>> N abs(NumberPath<N> numberPath) {
        BooleanBuilder builder = getAndRemoveCurrentBooleanBuilder();
        return createJPAQuery().select(numberPath.abs()).from($).where(builder).fetchOne();
    }

    protected <N extends Number & Comparable<?>> Double avg(NumberPath<N> numberPath) {
        BooleanBuilder builder = getAndRemoveCurrentBooleanBuilder();
        return createJPAQuery().select(numberPath.avg()).from($).where(builder).fetchOne();
    }

    protected <N extends Number & Comparable<?>> N max(NumberPath<N> numberPath) {
        BooleanBuilder builder = getAndRemoveCurrentBooleanBuilder();
        return createJPAQuery().select(numberPath.max()).from($).where(builder).fetchOne();
    }

    protected <N extends Number & Comparable<?>> N min(NumberPath<N> numberPath) {
        BooleanBuilder builder = getAndRemoveCurrentBooleanBuilder();
        return createJPAQuery().select(numberPath.min()).from($).where(builder).fetchOne();
    }

    protected long countWithoutRemoveCurrentBooleanBuilder() {
        BooleanBuilder builder = getCurrentBooleanBuilder();
        return createJPAQuery().from($).where(builder).fetchCount();
    }

    protected T unique() {
        BooleanBuilder builder = getAndRemoveCurrentBooleanBuilder();

        JPAQuery<T> query = createJPAQuery();

        query.select($).from($);

        List<EntityPath<?>> classes = getCurrentJoinFetchClasses();
        if (classes != null) {
            removeCurrentJoinFetchClasses();
            for (EntityPath<?> clazz : classes) {
                query.join(clazz).fetch();
            }
        }

        classes = getCurrentLeftJoinFetchClasses();
        if (classes != null) {
            removeCurrentLeftJoinFetchClasses();
            for (EntityPath<?> clazz : classes) {
                query.leftJoin(clazz).fetch();
            }
        }

        query.where(builder);

        return query.fetchOne();
    }

    protected List<T> list(long offset, long limit) {
        return list(offset, limit, $);
    }

    protected <RT> List<RT> list(long offset, long limit, Expression<RT> expr) {
        JPAQuery<RT> query = buildQuery();

        query.select(expr);

        List<OrderSpecifier<?>> orders = getCurrentOrder();
        if (orders != null) {
            removeCurrentOrder();
            for (OrderSpecifier<?> order : orders) {
                query.orderBy(order);
            }
        }

        if (offset < 0 || limit < 0) {
            return query.fetch();
        }

        return query.offset(offset).limit(limit).fetch();
    }

    protected List<Tuple> list(long offset, long limit, Expression<?>... exprs) {

        JPAQuery<Tuple> query = buildQuery(exprs);

        List<OrderSpecifier<?>> orders = getCurrentOrder();
        if (orders != null) {
            removeCurrentOrder();
            for (OrderSpecifier<?> order : orders) {
                query.orderBy(order);
            }
        }

        if (offset < 0 || limit < 0) {
            return query.fetch();
        }

        return query.offset(offset).limit(limit).fetch();
    }

    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
    protected void addSort(String sortField, String sortOrder, Map<String, Expression> mapping) {
        Assert.notNull(sortField);
        Assert.notNull(sortOrder);
        Assert.notNull(mapping);

        LOG.info("executing addSort method...");
        LOG.debug("sort field is : {}", sortField);
        LOG.debug("sort order is : {}", sortOrder);
        LOG.debug("sort mapping : ", mapping);

        Direction direction = Direction.fromString(sortOrder);

        Sort sort = new Sort(direction, sortField);
        for (Sort.Order order : sort) {
            com.querydsl.core.types.Order od = order.isAscending() ? com.querydsl.core.types.Order.ASC
                    : com.querydsl.core.types.Order.DESC;
            if (mapping.get(order.getProperty()) != null) {
                OrderSpecifier os = new OrderSpecifier(od, mapping.get(order.getProperty()));
                addOrder(os);
            }
        }
    }

    private <RT> JPAQuery<RT> buildQuery() {
        BooleanBuilder builder = getAndRemoveCurrentBooleanBuilder();

        JPAQuery<RT> query = new JPAQuery<RT>(getEntityManager());

        query.from($);

        List<EntityPath<?>> classes = getCurrentJoinFetchClasses();
        if (classes != null) {
            removeCurrentJoinFetchClasses();
            for (EntityPath<?> clazz : classes) {
                query.join(clazz).fetch();
            }
        }

        classes = getCurrentLeftJoinFetchClasses();
        if (classes != null) {
            removeCurrentLeftJoinFetchClasses();
            for (EntityPath<?> clazz : classes) {
                query.leftJoin(clazz).fetch();
            }
        }

        query.where(builder);
        return query;
    }

    private JPAQuery<Tuple> buildQuery(Expression<?>... exprs) {
        BooleanBuilder builder = getAndRemoveCurrentBooleanBuilder();

        JPAQuery<Tuple> query = createJPAQuery().select(exprs).from($);

        List<EntityPath<?>> classes = getCurrentJoinFetchClasses();
        if (classes != null) {
            removeCurrentJoinFetchClasses();
            for (EntityPath<?> clazz : classes) {
                query.join(clazz).fetch();
            }
        }

        classes = getCurrentLeftJoinFetchClasses();
        if (classes != null) {
            removeCurrentLeftJoinFetchClasses();
            for (EntityPath<?> clazz : classes) {
                query.leftJoin(clazz).fetch();
            }
        }

        query.where(builder);
        return query;
    }

    protected List<T> list() {
        return list(-1, -1);
    }

    protected List<Tuple> list(Expression<?>... exprs) {
        return list(-1, -1, exprs);
    }

    protected Tuple unique(Expression<?>... exprs) {
        JPAQuery<Tuple> query = buildQuery(exprs);

        List<OrderSpecifier<?>> orders = getCurrentOrder();
        if (orders != null) {
            removeCurrentOrder();
            for (OrderSpecifier<?> order : orders) {
                query.orderBy(order);
            }
        }

        return query.fetchOne();
    }

    protected <RT> List<RT> list(Expression<RT> expr) {
        return list(-1, -1, expr);
    }

    protected Page<T> findAll(Pageable pageable) {

        int offset = 0;
        int limit = -1;

        if (pageable != null) {
            offset = pageable.getOffset();
            limit = pageable.getPageSize();
            if (pageable.getSort() != null) {
                Sort sort = pageable.getSort();
                addSort(sort);
            }
        }

        long total = countWithoutRemoveCurrentBooleanBuilder();

        List<T> content = list(offset, limit);
        return new PageImpl<T>(content, pageable, total);
    }

    protected void addSort(Sort sort) {
        if (sort == null) {
            return;
        }

        for (Order order : sort) {
            addOrder(order);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void addOrder(Order order) {
        if (order == null) {
            return;
        }

        com.querydsl.core.types.Order qOrder = com.querydsl.core.types.Order.DESC;
        if (Sort.Direction.ASC == order.getDirection()) {
            qOrder = com.querydsl.core.types.Order.ASC;
        }

        try {
            Expression<?> qPath = getFieldValue($, order.getProperty());

            OrderSpecifier<?> orderSpecifier = new OrderSpecifier(qOrder, qPath);

            addOrder(orderSpecifier);

        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            LOG.error(order.getProperty() + " " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("deprecation")
    static public Class<?> getFieldType(Class<?> type, String propertyName)
            throws IllegalAccessException, NoSuchFieldException {
        Assert.hasText(propertyName);
        Field field = getField(type, propertyName);

        return field.getType();
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
    static public <T extends Comparable> Expression<T> getFieldValue(Object object, String propertyName)
            throws IllegalAccessException, NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        Field field = getField(object.getClass(), propertyName);
        field.setAccessible(true);

        return (Expression<T>) field.get(object);
    }

    private static Field getField(final Class<?> claszz, final String propertyName) throws NoSuchFieldException {
        Field field = null;

        Class<?> theClass = claszz;

        do {
            try {
                field = theClass.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                field = null;
            }
            theClass = theClass.getSuperclass();
        } while (field == null && theClass != null);

        if (field == null) {
            throw new NoSuchFieldException();
        }

        return field;
    }

    // ---------------------------------------------------------------------------------------------

    protected <N extends Number & Comparable<?>> void notIn(NumberPath<N> numberPath, Collection<N> values) {
        if (values != null && !values.isEmpty()) {
            BooleanBuilder builder = getCurrentBooleanBuilder();

            if (values.size() == 1) {
                builder.and(numberPath.ne(values.iterator().next()));
            } else {
                builder.and(numberPath.notIn(values));
            }
        }
    }

    protected <N extends Number & Comparable<?>> void notIn(StringPath examSubjectDesc, List<String> subjects) {
        if (subjects != null && !subjects.isEmpty()) {
            BooleanBuilder builder = getCurrentBooleanBuilder();

            if (subjects.size() == 1) {
                builder.and(examSubjectDesc.ne(subjects.iterator().next()));
            } else {
                builder.and(examSubjectDesc.notIn(subjects));
            }
        }
    }
}
