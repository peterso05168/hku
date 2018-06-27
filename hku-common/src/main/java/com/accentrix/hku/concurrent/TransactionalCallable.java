package com.accentrix.hku.concurrent;

import java.util.concurrent.Callable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public abstract class TransactionalCallable<V> extends OpenEntityManagerInViewFilter implements Callable<V> {

    private static final Logger log = LoggerFactory.getLogger(TransactionalCallable.class);

    @Override
    public V call() throws Exception {
        EntityManagerFactory emf = lookupEntityManagerFactory();
        boolean participate = false;

        if (TransactionSynchronizationManager.hasResource(emf)) {
            // Do not modify the EntityManager: just set the participate flag.
            participate = true;
        } else {
            log.debug("Opening JPA EntityManager in OpenEntityManagerInViewFilter");
            try {
                EntityManager em = createEntityManager(emf);
                TransactionSynchronizationManager.bindResource(emf, new EntityManagerHolder(em));
            } catch (PersistenceException ex) {
                throw new DataAccessResourceFailureException("Could not create JPA EntityManager", ex);
            }
        }
        try {
            return doCall();
        } finally {
            if (!participate) {
                EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager
                        .unbindResource(emf);
                log.debug("Closing JPA EntityManager in OpenEntityManagerInViewFilter");
                EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
            }
        }
    }

    public abstract V doCall();

}
