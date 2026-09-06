package io.github.ralfspoeth.raru;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionManager;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.ManagedConnectionFactory;
import javax.security.auth.Subject;

/**
 * This class implements the interface {@link ConnectionManager}
 * in a simplistic way for use in non-managed environments
 * as required by the JCA specification.
 * <p>
 * This implementation of the {@link ConnectionManager} interface
 * simply does not provide any kind of resource pooling.
 * <p>
 * The implementation is not strictly required by the JCA.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public class DefaultConnectionManager implements ConnectionManager {

    public static final ConnectionManager INSTANCE = new DefaultConnectionManager();

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    /**
     * Delegates the call to {@link
     * ManagedConnectionFactory#createManagedConnection(Subject, ConnectionRequestInfo)},
     * thus allocating a new managed connection every time this
     * method is called.
     *
     * @param mcf           the factory instance, may not be {@code null}.
     * @param cxRequestInfo the request parameters
     * @return the managed connection returned by the factory with a {@code null} {@link Subject}
     * @throws ResourceException rethrows mcfs exceptions
     */
    @Override
    public Object allocateConnection(ManagedConnectionFactory mcf, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        return mcf.createManagedConnection(null, cxRequestInfo); // todo
    }

}
