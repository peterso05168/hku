/*
 * $Id: OpenEntityManagerExecutor.java 12790 2015-02-13 08:52:17Z jierong $
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
package com.accentrix.hku.jpa;

import java.util.concurrent.Callable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.accentrix.hku.util.SpringContextHolder;

public class OpenEntityManagerExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(OpenEntityManagerExecutor.class);

    /**
     * Default EntityManagerFactory bean name: "entityManagerFactory". Only
     * applies when no "persistenceUnitName" param has been specified.
     * 
     * @see #setEntityManagerFactoryBeanName
     * @see #setPersistenceUnitName
     */
    public static final String DEFAULT_ENTITY_MANAGER_FACTORY_BEAN_NAME = "entityManagerFactory";

    private volatile EntityManagerFactory entityManagerFactory;

    public <T> T execute(Callable<T> callable) {

        EntityManagerFactory emf = lookupEntityManagerFactory();

        LOG.debug("Opening JPA EntityManager in OpenEntityManagerExecutor");
        try {
            EntityManager em = createEntityManager(emf);
            EntityManagerHolder emHolder = new EntityManagerHolder(em);
            TransactionSynchronizationManager.bindResource(emf, emHolder);

        } catch (PersistenceException ex) {
            throw new DataAccessResourceFailureException("Could not create JPA EntityManager", ex);
        }

        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        finally {
            EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager.unbindResource(emf);
            LOG.debug("Closing JPA EntityManager in OpenEntityManagerExecutor");
            EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
        }
    }

    /**
     * Look up the EntityManagerFactory that this filter should use, taking the
     * current HTTP request as argument.
     * <p>
     * The default implementation delegates to the
     * {@code lookupEntityManagerFactory} without arguments, caching the
     * EntityManagerFactory reference once obtained.
     * 
     * @return the EntityManagerFactory to use
     * @see #lookupEntityManagerFactory()
     */
    protected EntityManagerFactory lookupEntityManagerFactory() {
        if (this.entityManagerFactory == null) {
            this.entityManagerFactory = _lookupEntityManagerFactory();
        }
        return this.entityManagerFactory;
    }

    /**
     * Look up the EntityManagerFactory that this filter should use.
     * <p>
     * The default implementation looks for a bean with the specified name in
     * Spring's root application context.
     * 
     * @return the EntityManagerFactory to use
     * @see #getEntityManagerFactoryBeanName
     */
    protected EntityManagerFactory _lookupEntityManagerFactory() {
        return SpringContextHolder.getBean(EntityManagerFactory.class);
    }

    /**
     * Create a JPA EntityManager to be bound to a request.
     * <p>
     * Can be overridden in subclasses.
     * 
     * @param emf
     *            the EntityManagerFactory to use
     * @see javax.persistence.EntityManagerFactory#createEntityManager()
     */
    protected EntityManager createEntityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }
}
