package com.example.mybatisderive.extend;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.core.InfrastructureProxy;
import org.springframework.lang.Nullable;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.util.List;

public class DevTransactionSynchronizationUtils {

    private static final Log logger = LogFactory.getLog(DevTransactionSynchronizationUtils.class);

    private static final boolean aopAvailable = ClassUtils.isPresent(
            "org.springframework.aop.scope.ScopedObject", DevTransactionSynchronizationUtils.class.getClassLoader());

    public static boolean sameResourceFactory(ResourceTransactionManager tm, Object resourceFactory) {
        return unwrapResourceIfNecessary(tm.getResourceFactory()).equals(unwrapResourceIfNecessary(resourceFactory));
    }

    public static Object unwrapResourceIfNecessary(Object resource) {
        Assert.notNull(resource, "Resource must not be null");
        Object resourceRef = resource;
        // unwrap infrastructure proxy
        if (resourceRef instanceof InfrastructureProxy) {
            resourceRef = ((InfrastructureProxy) resourceRef).getWrappedObject();
        }
        if (aopAvailable) {
            // now unwrap scoped proxy
            resourceRef = DevTransactionSynchronizationUtils.ScopedProxyUnwrapper.unwrapIfNecessary(resourceRef);
        }
        return resourceRef;
    }

    public static void triggerFlush() {
        for (TransactionSynchronization synchronization : DevTransactionSynchronizationManager.getSynchronizations()) {
            synchronization.flush();
        }
    }

    public static void triggerBeforeCommit(boolean readOnly) {
        for (TransactionSynchronization synchronization : DevTransactionSynchronizationManager.getSynchronizations()) {
            synchronization.beforeCommit(readOnly);
        }
    }

    public static void triggerBeforeCompletion() {
        for (TransactionSynchronization synchronization : DevTransactionSynchronizationManager.getSynchronizations()) {
            try {
                synchronization.beforeCompletion();
            }
            catch (Throwable ex) {
                logger.debug("TransactionSynchronization.beforeCompletion threw exception", ex);
            }
        }
    }

    public static void triggerAfterCommit() {
        invokeAfterCommit(DevTransactionSynchronizationManager.getSynchronizations());
    }

    public static void invokeAfterCommit(@Nullable List<TransactionSynchronization> synchronizations) {
        if (synchronizations != null) {
            for (TransactionSynchronization synchronization : synchronizations) {
                synchronization.afterCommit();
            }
        }
    }

    public static void triggerAfterCompletion(int completionStatus) {
        List<TransactionSynchronization> synchronizations = DevTransactionSynchronizationManager.getSynchronizations();
        invokeAfterCompletion(synchronizations, completionStatus);
    }

    public static void invokeAfterCompletion(@Nullable List<TransactionSynchronization> synchronizations,
                                             int completionStatus) {

        if (synchronizations != null) {
            for (TransactionSynchronization synchronization : synchronizations) {
                try {
                    synchronization.afterCompletion(completionStatus);
                }
                catch (Throwable ex) {
                    logger.debug("TransactionSynchronization.afterCompletion threw exception", ex);
                }
            }
        }
    }

    private static class ScopedProxyUnwrapper {
        public static Object unwrapIfNecessary(Object resource) {
            if (resource instanceof ScopedObject) {
                return ((ScopedObject) resource).getTargetObject();
            }
            else {
                return resource;
            }
        }
    }

}
