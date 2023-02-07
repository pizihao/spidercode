package com.example.mybatisderive.extend;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.transaction.*;
import org.springframework.transaction.support.*;
import org.springframework.util.Assert;

import java.lang.reflect.UndeclaredThrowableException;

public class DevTransactionTemplate extends DefaultTransactionDefinition
        implements TransactionOperations, InitializingBean {

    protected final Log logger = LogFactory.getLog(getClass());

    @Nullable
    private PlatformTransactionManager transactionManager;

    public DevTransactionTemplate() {
    }

    public DevTransactionTemplate(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public DevTransactionTemplate(PlatformTransactionManager transactionManager, TransactionDefinition transactionDefinition) {
        super(transactionDefinition);
        this.transactionManager = transactionManager;
    }

    public void setTransactionManager(@Nullable PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Nullable
    public PlatformTransactionManager getTransactionManager() {
        return this.transactionManager;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.transactionManager == null) {
            throw new IllegalArgumentException("Property 'transactionManager' is required");
        }
    }

    @Override
    @Nullable
    public <T> T execute(TransactionCallback<T> action) throws TransactionException {
        Assert.state(this.transactionManager != null, "No PlatformTransactionManager set");
        if (this.transactionManager instanceof CallbackPreferringPlatformTransactionManager) {
            return ((CallbackPreferringPlatformTransactionManager) this.transactionManager).execute(this, action);
        }
        else {
            TransactionStatus status = this.transactionManager.getTransaction(this);
            T result;
            try {
                result = action.doInTransaction(status);
            }
            catch (RuntimeException | Error ex) {
                rollbackOnException(status, ex);
                throw ex;
            }
            catch (Throwable ex) {
                rollbackOnException(status, ex);
                throw new UndeclaredThrowableException(ex, "TransactionCallback threw undeclared checked exception");
            }
            this.transactionManager.commit(status);
            return result;
        }
    }

    private void rollbackOnException(TransactionStatus status, Throwable ex) throws TransactionException {
        Assert.state(this.transactionManager != null, "No PlatformTransactionManager set");
        logger.debug("Initiating transaction rollback on application exception", ex);
        try {
            this.transactionManager.rollback(status);
        }
        catch (TransactionSystemException ex2) {
            logger.error("Application exception overridden by rollback exception", ex);
            ex2.initApplicationException(ex);
            throw ex2;
        }
        catch (RuntimeException | Error ex2) {
            logger.error("Application exception overridden by rollback exception", ex);
            throw ex2;
        }
    }


    @Override
    public boolean equals(@Nullable Object other) {
        return (this == other || (super.equals(other) && (!(other instanceof DevTransactionTemplate) ||
                getTransactionManager() == ((DevTransactionTemplate) other).getTransactionManager())));
    }

}
