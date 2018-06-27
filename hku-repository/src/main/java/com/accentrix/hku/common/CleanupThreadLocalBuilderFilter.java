/*
 * $Id: CleanupThreadLocalBuilderFilter.java 22581 2017-02-28 09:50:46Z jyang $
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

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;

public class CleanupThreadLocalBuilderFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(CleanupThreadLocalBuilderFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } finally {
            BooleanBuilder builder = JpaDslQuery.BOOLEAN_BUILDERS.get();
            if (builder != null) {
                LOG.error("BooleanBuilder not removed from ThreadLocal");
                JpaDslQuery.BOOLEAN_BUILDERS.remove();
            }

            List<OrderSpecifier<?>> oderSpecs = JpaDslQuery.ORDERS.get();
            if (oderSpecs != null) {
                LOG.error("OrderSpecifier not removed from ThreadLocal");
                JpaDslQuery.ORDERS.remove();
            }

            List<EntityPath<?>> classes = JpaDslQuery.JOIN_FETCH_CLASSES.get();
            if (classes != null) {
                LOG.error("JoinFetchClasses not removed from ThreadLocal");
                JpaDslQuery.JOIN_FETCH_CLASSES.remove();
            }

            classes = JpaDslQuery.LEFT_JOIN_FETCH_CLASSES.get();
            if (classes != null) {
                LOG.error("LeftJoinFetchClasses not removed from ThreadLocal");
                JpaDslQuery.LEFT_JOIN_FETCH_CLASSES.remove();
            }
        }
    }

    @Override
    public void destroy() {
    }

}
