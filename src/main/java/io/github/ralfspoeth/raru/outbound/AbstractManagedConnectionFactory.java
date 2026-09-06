package io.github.ralfspoeth.raru.outbound;

import io.github.ralfspoeth.raru.DefaultConnectionManager;

import jakarta.resource.NotSupportedException;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.*;
import javax.security.auth.Subject;
import java.io.PrintWriter;
import java.util.Set;

/**
 * This class provides the boilerplate code for a
 * {@link ManagedConnectionFactory} in an outbound resource adapter.
 * <p>
 * It uses the {@link DefaultConnectionManager} in non-managed environments.
 * <p>
 * Resource adapter implementations must implement the methods
 * {@link ManagedConnectionFactory#createConnectionFactory(jakarta.resource.spi.ConnectionManager)} and
 * {@link #createManagedConnection(Subject, ConnectionRequestInfo)}, the former
 * providing an instance of the client-level connection factory and the
 * latter providing an instance of the physical managed connection.
 * <p>
 * This implementation does not support connection matching.
 * <p>
 * The actual implementation should use the {@link ConnectionDefinition}
 * annotation. It should furthermore comply with the JavaBeans
 * specification (No-arg constructor, properties with getters and setters,
 * must be serializable which it already is).
 * <p>
 * Remember to implement both {@link #equals(java.lang.Object)} and
 * {@link #hashCode()}.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractManagedConnectionFactory implements
        ManagedConnectionFactory, ResourceAdapterAssociation {

    /**
     * Feature is not supported; throws a {@link NotSupportedException}.
     *
     * @param connectionSet recent connections
     * @param subject       the security subject
     * @param cxRequestInfo request information
     * @return nothing; always throws an exception
     * @throws NotSupportedException always; feature is not implemented
     */
    @Override
    public ManagedConnection matchManagedConnections(
            Set connectionSet, Subject subject,
            ConnectionRequestInfo cxRequestInfo)
            throws ResourceException {
        throw new NotSupportedException();
    }

    /**
     * Instantiate a new connection using the default connection manager
     * shipped with this library; see {@link DefaultConnectionManager}.
     *
     * @return a new connection
     * @throws ResourceException see {@link #createConnectionFactory(jakarta.resource.spi.ConnectionManager)}
     */
    @Override
    public Object createConnectionFactory() throws ResourceException {
        return createConnectionFactory(DefaultConnectionManager.getInstance());
    }

    protected PrintWriter logWriter;

    @Override
    public void setLogWriter(PrintWriter out) {
        this.logWriter = out;
    }

    @Override
    public PrintWriter getLogWriter() {
        return logWriter;
    }

    protected ResourceAdapter ra;

    @Override
    public ResourceAdapter getResourceAdapter() {
        return ra;
    }

    @Override
    public void setResourceAdapter(ResourceAdapter ra) throws ResourceException {
        if (this.ra != null)
            throw new ResourceException("resource adapter already set");
        this.ra = ra;
    }
}
