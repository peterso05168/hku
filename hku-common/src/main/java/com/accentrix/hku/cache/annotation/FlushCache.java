/*
 * $Id: FlushCache.java 14589 2015-07-23 06:47:32Z jyang $
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
package com.accentrix.hku.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.accentrix.hku.cache.CacheScope;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface FlushCache {

    String group();

    CacheScope cacheScope() default CacheScope.application;
}
