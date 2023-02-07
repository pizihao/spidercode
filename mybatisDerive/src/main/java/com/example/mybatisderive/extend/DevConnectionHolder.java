package com.example.mybatisderive.extend;

import org.springframework.jdbc.datasource.ConnectionHandle;
import org.springframework.jdbc.datasource.ConnectionHolder;

import java.sql.Connection;


public class DevConnectionHolder extends ConnectionHolder {

    ConnectionHolder connectionHolder;

    public DevConnectionHolder(ConnectionHandle connectionHandle, ConnectionHolder connectionHolder) {
        super(connectionHandle);
        this.connectionHolder = connectionHolder;
    }

    public DevConnectionHolder(Connection connection, ConnectionHolder connectionHolder) {
        super(connection);
        this.connectionHolder = connectionHolder;
    }

    public DevConnectionHolder(Connection connection, boolean transactionActive, ConnectionHolder connectionHolder) {
        super(connection, transactionActive);
        this.connectionHolder = connectionHolder;
    }

    public DevConnectionHolder(ConnectionHolder connectionHolder) {
        super(connectionHolder.getConnection());
        this.connectionHolder = connectionHolder;
    }

    protected void setTransactionActive(boolean transactionActive) {
        super.setTransactionActive(transactionActive);
    }
}
