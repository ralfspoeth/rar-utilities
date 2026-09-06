package io.github.ralfspoeth.raru.outbound;

import io.github.ralfspoeth.raru.DefaultConnectionManager;

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
 * This implementation does not implement connection matching; see
 * {@link #matchManagedConnections(Set, Subject, ConnectionRequestInfo)}.
 * <p>
 * The actual implementation should use the {@link ConnectionDefinition}
 * annotation. It should furthermore comply with the JavaBeans
 * specification (No-arg constructor, properties with getters and setters,
 * must be serializable, which it already is through
 * {@link ManagedConnectionFactory}). Keep any field that is not itself
 * serializable {@code transient}, as this class does for its log writer and
 * its resource adapter.
 * <p>
 * Remember to implement both {@link #equals(java.lang.Object)} and
 * {@link #hashCode()}.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractManagedConnectionFactory implements
        ManagedConnectionFactory, ResourceAdapterAssociation {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    /**
     * Returns {@code null}, meaning that none of the candidates in
     * {@code connectionSet} is an acceptable match and the caller should create a
     * new connection instead.
     * <p>
     * {@code null} is what the contract of
     * {@link ManagedConnectionFactory#matchManagedConnections(Set, Subject, ConnectionRequestInfo)}
     * prescribes for the no-match case. Throwing instead would turn an ordinary
     * pool miss into a failure in every pooling container.
     * <p>
     * Subclasses whose connections should be reused by the container's pool must
     * override this method: pick the candidates you recognise out of
     * {@code connectionSet} and compare them against {@code subject} and
     * {@code cxRequestInfo}.
     *
     * @param connectionSet the candidate connections
     * @param subject       the security subject
     * @param cxRequestInfo request information
     * @return {@code null}, always, unless overridden
     * @throws ResourceException never thrown here; declared for the benefit of overriding subclasses
     */
    @Override
    public ManagedConnection matchManagedConnections(
            Set connectionSet, Subject subject,
            ConnectionRequestInfo cxRequestInfo)
            throws ResourceException {
        return null;
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

    /**
     * Not part of the serializable state: a {@link PrintWriter} is not
     * serializable, and the container hands a fresh one to a deserialized
     * factory through {@link #setLogWriter(PrintWriter)}.
     */
    protected transient PrintWriter logWriter;

    @Override
    public void setLogWriter(PrintWriter out) {
        this.logWriter = out;
    }

    @Override
    public PrintWriter getLogWriter() {
        return logWriter;
    }

    /**
     * Not part of the serializable state: the association with the resource
     * adapter is re-established by the container after deserialization through
     * {@link #setResourceAdapter(ResourceAdapter)}.
     */
    protected transient ResourceAdapter ra;

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
