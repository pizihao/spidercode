package com.example.mybatisderive.extend;


import org.springframework.core.OrderComparator;
import org.springframework.lang.Nullable;
import org.springframework.transaction.support.*;
import org.springframework.util.Assert;

import java.util.*;

public abstract class DevTransactionSynchronizationManager {

    private DevTransactionSynchronizationManager(){}

    private static final ThreadLocal<Map<Object, Object>> resources =
            new NameInheritableThreadLocal<>("Transactional resources");

    private static final ThreadLocal<Set<TransactionSynchronization>> synchronizations =
            new NameInheritableThreadLocal<>("Transaction synchronizations");

    private static final ThreadLocal<String> currentTransactionName =
            new NameInheritableThreadLocal<>("Current transaction name");

    private static final ThreadLocal<Boolean> currentTransactionReadOnly =
            new NameInheritableThreadLocal<>("Current transaction read-only status");

    private static final ThreadLocal<Integer> currentTransactionIsolationLevel =
            new NameInheritableThreadLocal<>("Current transaction isolation level");

    private static final ThreadLocal<Boolean> actualTransactionActive =
            new NameInheritableThreadLocal<>("Actual transaction active");

    public static Map<Object, Object> getResourceMap() {
        Map<Object, Object> map = resources.get();
        return (map != null ? Collections.unmodifiableMap(map) : Collections.emptyMap());
    }

    public static boolean hasResource(Object key) {
        Object actualKey = TransactionSynchronizationUtils.unwrapResourceIfNecessary(key);
        Object value = doGetResource(actualKey);
        return (value != null);
    }

    @Nullable
    public static Object getResource(Object key) {
        Object actualKey = TransactionSynchronizationUtils.unwrapResourceIfNecessary(key);
        return doGetResource(actualKey);
    }

    @Nullable
    private static Object doGetResource(Object actualKey) {
        Map<Object, Object> map = resources.get();
        if (map == null) {
            return null;
        }
        Object value = map.get(actualKey);
        if (value instanceof ResourceHolder && ((ResourceHolder) value).isVoid()) {
            map.remove(actualKey);
            if (map.isEmpty()) {
                resources.remove();
            }
            value = null;
        }
        return value;
    }

    public static void bindResource(Object key, Object value) throws IllegalStateException {
        Object actualKey = TransactionSynchronizationUtils.unwrapResourceIfNecessary(key);
        Assert.notNull(value, "Value must not be null");
        Map<Object, Object> map = resources.get();
        if (map == null) {
            map = new HashMap<>();
            resources.set(map);
        }
        Object oldValue = map.put(actualKey, value);
        if (oldValue instanceof ResourceHolder && ((ResourceHolder) oldValue).isVoid()) {
            oldValue = null;
        }
        if (oldValue != null) {
            throw new IllegalStateException(
                    "Already value [" + oldValue + "] for key [" + actualKey + "] bound to thread");
        }
    }

    public static Object unbindResource(Object key) throws IllegalStateException {
        Object actualKey = TransactionSynchronizationUtils.unwrapResourceIfNecessary(key);
        Object value = doUnbindResource(actualKey);
        if (value == null) {
            throw new IllegalStateException("No value for key [" + actualKey + "] bound to thread");
        }
        return value;
    }

    @Nullable
    public static Object unbindResourceIfPossible(Object key) {
        Object actualKey = TransactionSynchronizationUtils.unwrapResourceIfNecessary(key);
        return doUnbindResource(actualKey);
    }

    @Nullable
    private static Object doUnbindResource(Object actualKey) {
        Map<Object, Object> map = resources.get();
        if (map == null) {
            return null;
        }
        Object value = map.remove(actualKey);
        if (map.isEmpty()) {
            resources.remove();
        }
        if (value instanceof ResourceHolder && ((ResourceHolder) value).isVoid()) {
            value = null;
        }
        return value;
    }

    public static boolean isSynchronizationActive() {
        return (synchronizations.get() != null);
    }

    public static void initSynchronization() throws IllegalStateException {
        if (isSynchronizationActive()) {
            throw new IllegalStateException("Cannot activate transaction synchronization - already active");
        }
        synchronizations.set(new LinkedHashSet<>());
    }

    public static void registerSynchronization(TransactionSynchronization synchronization)
            throws IllegalStateException {

        Assert.notNull(synchronization, "TransactionSynchronization must not be null");
        Set<TransactionSynchronization> synchs = synchronizations.get();
        if (synchs == null) {
            throw new IllegalStateException("Transaction synchronization is not active");
        }
        synchs.add(synchronization);
    }

    public static List<TransactionSynchronization> getSynchronizations() throws IllegalStateException {
        Set<TransactionSynchronization> synchs = synchronizations.get();
        if (synchs == null) {
            throw new IllegalStateException("Transaction synchronization is not active");
        }
        if (synchs.isEmpty()) {
            return Collections.emptyList();
        }
        else {
            List<TransactionSynchronization> sortedSynchs = new ArrayList<>(synchs);
            OrderComparator.sort(sortedSynchs);
            return Collections.unmodifiableList(sortedSynchs);
        }
    }

    public static void clearSynchronization() throws IllegalStateException {
        if (!isSynchronizationActive()) {
            throw new IllegalStateException("Cannot deactivate transaction synchronization - not active");
        }
        synchronizations.remove();
    }

    public static void setCurrentTransactionName(@Nullable String name) {
        currentTransactionName.set(name);
    }

    @Nullable
    public static String getCurrentTransactionName() {
        return currentTransactionName.get();
    }

    public static void setCurrentTransactionReadOnly(boolean readOnly) {
        currentTransactionReadOnly.set(readOnly ? Boolean.TRUE : null);
    }

    public static boolean isCurrentTransactionReadOnly() {
        return (currentTransactionReadOnly.get() != null);
    }

    public static void setCurrentTransactionIsolationLevel(@Nullable Integer isolationLevel) {
        currentTransactionIsolationLevel.set(isolationLevel);
    }

    @Nullable
    public static Integer getCurrentTransactionIsolationLevel() {
        return currentTransactionIsolationLevel.get();
    }

    public static void setActualTransactionActive(boolean active) {
        actualTransactionActive.set(active ? Boolean.TRUE : null);
    }

    public static boolean isActualTransactionActive() {
        return (actualTransactionActive.get() != null);
    }

    public static void clear() {
        synchronizations.remove();
        currentTransactionName.remove();
        currentTransactionReadOnly.remove();
        currentTransactionIsolationLevel.remove();
        actualTransactionActive.remove();
    }
}
