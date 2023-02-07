package com.example.mybatisderive.extend;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.lang.Nullable;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;

public class DevDataSourceTransactionManager extends DataSourceTransactionManager {

    public DevDataSourceTransactionManager() {
        super();
    }

    public DevDataSourceTransactionManager(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Object doGetTransaction() {
        DevDataSourceTransactionObject txObject = new DevDataSourceTransactionObject();
        txObject.setSavepointAllowed(isNestedTransactionAllowed());
        ConnectionHolder conHolder =
                (ConnectionHolder) DevTransactionSynchronizationManager.getResource(obtainDataSource());
        txObject.setConnectionHolder(conHolder, false);
        return txObject;
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        DevDataSourceTransactionObject txObject = (DevDataSourceTransactionObject) transaction;
        Connection con = null;
        try {
            ConnectionHolder holder = txObject.getConnectionHolder();
            DevConnectionHolder connectionHolder = new DevConnectionHolder(holder);
            if (!txObject.hasConnectionHolder() ||
                    connectionHolder.isSynchronizedWithTransaction()) {
                Connection newCon = obtainDataSource().getConnection();
                if (logger.isDebugEnabled()) {
                    logger.debug("Acquired Connection [" + newCon + "] for JDBC transaction");
                }
                txObject.setConnectionHolder(new ConnectionHolder(newCon), true);
            }

            connectionHolder.setSynchronizedWithTransaction(true);
            con = connectionHolder.getConnection();

            Integer previousIsolationLevel = DataSourceUtils.prepareConnectionForTransaction(con, definition);
            txObject.setPreviousIsolationLevel(previousIsolationLevel);
            txObject.setReadOnly(definition.isReadOnly());

            if (con.getAutoCommit()) {
                txObject.setMustRestoreAutoCommit(true);
                if (logger.isDebugEnabled()) {
                    logger.debug("Switching JDBC Connection [" + con + "] to manual commit");
                }
                con.setAutoCommit(false);
            }

            prepareTransactionalConnection(con, definition);
            connectionHolder.setTransactionActive(true);

            int timeout = determineTimeout(definition);
            if (timeout != TransactionDefinition.TIMEOUT_DEFAULT) {
                connectionHolder.setTimeoutInSeconds(timeout);
            }

            if (txObject.isNewConnectionHolder()) {
                DevTransactionSynchronizationManager.bindResource(obtainDataSource(), connectionHolder);
            }
        } catch (Throwable ex) {
            if (txObject.isNewConnectionHolder()) {
                DataSourceUtils.releaseConnection(con, obtainDataSource());
                txObject.setConnectionHolder(null, false);
            }
            throw new CannotCreateTransactionException("Could not open JDBC Connection for transaction", ex);
        }
    }

    @Override
    protected Object doSuspend(Object transaction) {
        DevDataSourceTransactionObject txObject = (DevDataSourceTransactionObject) transaction;
        txObject.setConnectionHolder(null);
        return DevTransactionSynchronizationManager.unbindResource(obtainDataSource());
    }

    @Override
    protected void doResume(@Nullable Object transaction, Object suspendedResources) {
        DevTransactionSynchronizationManager.bindResource(obtainDataSource(), suspendedResources);
    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        DevDataSourceTransactionObject txObject = (DevDataSourceTransactionObject) transaction;

        // Remove the connection holder from the thread, if exposed.
        if (txObject.isNewConnectionHolder()) {
            DevTransactionSynchronizationManager.unbindResource(obtainDataSource());
        }

        // Reset connection.
        Connection con = txObject.getConnectionHolder().getConnection();
        try {
            if (txObject.isMustRestoreAutoCommit()) {
                con.setAutoCommit(true);
            }
            DataSourceUtils.resetConnectionAfterTransaction(
                    con, txObject.getPreviousIsolationLevel(), txObject.isReadOnly());
        } catch (Throwable ex) {
            logger.debug("Could not reset JDBC Connection after transaction", ex);
        }

        if (txObject.isNewConnectionHolder()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Releasing JDBC Connection [" + con + "] after transaction");
            }
            DataSourceUtils.releaseConnection(con, this.getDataSource());
        }

        txObject.getConnectionHolder().clear();
    }

    private static class DevDataSourceTransactionObject extends JdbcTransactionObjectSupport {

        private boolean newConnectionHolder;

        private boolean mustRestoreAutoCommit;

        public void setConnectionHolder(@Nullable ConnectionHolder connectionHolder, boolean newConnectionHolder) {
            super.setConnectionHolder(connectionHolder);
            this.newConnectionHolder = newConnectionHolder;
        }

        public boolean isNewConnectionHolder() {
            return this.newConnectionHolder;
        }

        public void setMustRestoreAutoCommit(boolean mustRestoreAutoCommit) {
            this.mustRestoreAutoCommit = mustRestoreAutoCommit;
        }

        public boolean isMustRestoreAutoCommit() {
            return this.mustRestoreAutoCommit;
        }

        public void setRollbackOnly() {
            getConnectionHolder().setRollbackOnly();
        }

        @Override
        public boolean isRollbackOnly() {
            return getConnectionHolder().isRollbackOnly();
        }

        @Override
        public void flush() {
            if (DevTransactionSynchronizationManager.isSynchronizationActive()) {
                DevTransactionSynchronizationUtils.triggerFlush();
            }
        }
    }
}
