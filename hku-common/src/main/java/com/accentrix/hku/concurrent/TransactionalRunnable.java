package com.accentrix.hku.concurrent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public abstract class TransactionalRunnable extends OpenEntityManagerInViewFilter implements Runnable {

    @Override
    public void run() {
        EntityManagerFactory emf = lookupEntityManagerFactory();
        boolean participate = false;

        if (TransactionSynchronizationManager.hasResource(emf)) {
            // Do not modify the EntityManager: just set the participate flag.
            participate = true;
        } else {
            logger.debug("Opening JPA EntityManager in OpenEntityManagerInViewFilter");
            try {
                EntityManager em = createEntityManager(emf);
                TransactionSynchronizationManager.bindResource(emf, new EntityManagerHolder(em));
            } catch (PersistenceException ex) {
                throw new DataAccessResourceFailureException("Could not create JPA EntityManager", ex);
            }
        }
        try {
            doRun();
        } finally {
            if (!participate) {
                EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager
                        .unbindResource(emf);
                logger.debug("Closing JPA EntityManager in OpenEntityManagerInViewFilter");
                EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
            }
        }
    }

    protected abstract void doRun();
}
