package io.github.ralfspoeth.raru.outbound;

import jakarta.resource.spi.ConnectionEvent;
import jakarta.resource.spi.ConnectionEventListener;
import jakarta.resource.spi.ManagedConnection;
import java.io.PrintWriter;
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
 * Note that {@link ManagedConnection} does <em>not</em> extend
 * {@link java.io.Serializable} and implementations are not required to be
 * serializable; a managed connection wraps a live physical connection, which
 * cannot meaningfully be written to a stream. Earlier versions of this class
 * declared {@code Serializable} anyway, which was both unnecessary and
 * unimplementable.
 * <p>
 * The managed connection and the application-level connection handle are
 * assumed to be associated with each other; the container may re-associate a
 * handle with a different managed connection through
 * {@link #associateConnection(Object)}, and may dissociate it again through
 * {@link #cleanup()}.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractManagedConnection<T> implements ManagedConnection {

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
