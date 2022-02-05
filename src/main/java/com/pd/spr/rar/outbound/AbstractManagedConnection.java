/*
 * Copyright (C) 2014 SPR
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.pd.spr.rar.outbound;

import jakarta.resource.spi.ConnectionEvent;
import jakarta.resource.spi.ConnectionEventListener;
import jakarta.resource.spi.ManagedConnection;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class may serve as the basis for managed
 * connections of a resource adapter.
 * <p>
 * As a managed connection represents the physical outbound connection
 * to the EIS, this object should provide all the business methods
 * that you want to make available to EJBs.
 * <p>
 * This abstract class already implements {@link Serializable}, making
 * any subclass serializable. This is a requirement for implementations
 * of {@link ManagedConnection}s.
 * <p>
 * The managed connection and the application-level connection handle
 * are assumed to be associated with each other. The container is
 * allowed to
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractManagedConnection<T> implements ManagedConnection, Serializable {

    protected T connection;

    /**
     * This method must be overridden such that the passed connection
     * is first disassociated from its current managed connection.
     *
     * @param connection the connection
     */
    @Override
    @SuppressWarnings("unchecked")
    public void associateConnection(Object connection) {
        this.connection = (T) connection;
    }

    /**
     * The default implementation just sets the reference to the
     * connection handle to {@code null}.
     */
    @Override
    public void cleanup() {
        this.connection = null;
    }

    /**
     * Empty implementation
     */
    @Override
    public void destroy() {
    }

    /**
     * uses a COW list because we assume rare changes and frequent
     * traversals.
     */
    private final List<ConnectionEventListener> listeners = new CopyOnWriteArrayList<>();

    @Override
    public void addConnectionEventListener(ConnectionEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeConnectionEventListener(ConnectionEventListener listener) {
        listeners.remove(listener);
    }

    protected void fireEvent(ConnectionEvent evt) {
        for (ConnectionEventListener l : listeners) {
            switch (evt.getId()) {
                case ConnectionEvent.CONNECTION_CLOSED:
                    l.connectionClosed(evt);
                    break;
                case ConnectionEvent.CONNECTION_ERROR_OCCURRED:
                    l.connectionErrorOccurred(evt);
                    break;
                case ConnectionEvent.LOCAL_TRANSACTION_COMMITTED:
                    l.localTransactionCommitted(evt);
                    break;
                case ConnectionEvent.LOCAL_TRANSACTION_ROLLEDBACK:
                    l.localTransactionRolledback(evt);
                    break;
                case ConnectionEvent.LOCAL_TRANSACTION_STARTED:
                    l.localTransactionStarted(evt);
                    break;
                default:
                    assert false;
            }
        }
    }

    protected void fireConnectionClosed() {
        fireEvent(new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED));
    }

    protected void fireConnectionErrorOccurred(Exception ex) {
        fireEvent(new ConnectionEvent(this, ConnectionEvent.CONNECTION_ERROR_OCCURRED, ex));
    }

    protected void fireLocalTransactionStarted() {
        fireEvent(new ConnectionEvent(this, ConnectionEvent.LOCAL_TRANSACTION_STARTED));
    }

    protected void fireLocalTransactionCommitted() {
        fireEvent(new ConnectionEvent(this, ConnectionEvent.LOCAL_TRANSACTION_COMMITTED));
    }

    protected void fireLocalTransactionRolledback() {
        fireEvent(new ConnectionEvent(this, ConnectionEvent.LOCAL_TRANSACTION_ROLLEDBACK));
    }

    private PrintWriter logWriter = null;

    @Override
    public PrintWriter getLogWriter() {
        return logWriter;
    }

    @Override
    public void setLogWriter(PrintWriter out) {
        this.logWriter = out;
    }
}
