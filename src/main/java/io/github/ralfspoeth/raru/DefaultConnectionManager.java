package io.github.ralfspoeth.raru;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionManager;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.ManagedConnection;
import jakarta.resource.spi.ManagedConnectionFactory;
import javax.security.auth.Subject;

import static java.util.Objects.requireNonNull;

/**
 * This class implements the interface {@link ConnectionManager}
 * in a simplistic way for use in non-managed environments
 * as required by the JCA specification.
 * <p>
 * This implementation of the {@link ConnectionManager} interface
 * does not provide any kind of resource pooling: each call to
 * {@link #allocateConnection(ManagedConnectionFactory, ConnectionRequestInfo)}
 * yields a brand new physical connection.
 * <p>
 * The implementation is not strictly required by the JCA.
 */
public class DefaultConnectionManager implements ConnectionManager {

    public static final ConnectionManager INSTANCE = new DefaultConnectionManager();

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new physical connection through the given factory and returns the
     * application-level connection handle obtained from it, as required by
     * {@link ConnectionManager#allocateConnection(ManagedConnectionFactory, ConnectionRequestInfo)}.
     * <p>
     * No pooling takes place: every invocation allocates a fresh
     * {@link ManagedConnection}.
     * <p>
     * The {@link Subject} passed on is {@code null}, which is the appropriate value
     * in a non-managed environment where no container-managed security context
     * exists; the resource adapter is expected to take its credentials from
     * {@code cxRequestInfo} or from its own configuration.
     * <p>
     * Should the handle not be obtainable, the freshly created managed connection
     * is destroyed again so that no physical connection leaks.
     *
     * @param mcf           the factory instance, may not be {@code null}
     * @param cxRequestInfo the request parameters, may be {@code null}
     * @return the application-level connection handle
     * @throws ResourceException rethrows the exceptions raised by {@code mcf}
     */
    @Override
    public Object allocateConnection(ManagedConnectionFactory mcf, ConnectionRequestInfo cxRequestInfo)
            throws ResourceException {
        var mc = requireNonNull(mcf, "managed connection factory")
                .createManagedConnection(null, cxRequestInfo);
        try {
            return mc.getConnection(null, cxRequestInfo);
        } catch (ResourceException | RuntimeException ex) {
            try {
                mc.destroy();
            } catch (ResourceException | RuntimeException suppressed) {
                ex.addSuppressed(suppressed);
            }
            throw ex;
        }
    }

}
