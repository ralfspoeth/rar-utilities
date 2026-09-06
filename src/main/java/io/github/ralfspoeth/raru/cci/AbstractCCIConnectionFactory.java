package io.github.ralfspoeth.raru.cci;

import io.github.ralfspoeth.raru.ci.AbstractConnectionFactory;

import jakarta.resource.NotSupportedException;
import jakarta.resource.Referenceable;
import jakarta.resource.ResourceException;
import jakarta.resource.cci.Connection;
import jakarta.resource.cci.ConnectionFactory;
import jakarta.resource.cci.RecordFactory;

/**
 * This class provides a default implementation
 * for the base interface {@link Referenceable}
 * of {@link ConnectionFactory}.
 * <p>
 * It builds upon the non-CCI version {@link AbstractConnectionFactory}
 * because of their similarity.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractCCIConnectionFactory extends AbstractConnectionFactory implements ConnectionFactory {

    /**
     * Calls {@link #getConnection(jakarta.resource.cci.ConnectionSpec)}
     * with {@code null} as spec parameter.
     *
     * @return connection instance
     * @throws ResourceException whenever the connection instantiation fails
     */
    @Override
    public Connection getConnection() throws ResourceException {
        return getConnection(null);
    }

    /**
     * The default implementation throws a {@link NotSupportedException}
     * indicating that the optional features of a record factory
     * are not supported.
     *
     * @return override to implement this feature; returns nothing
     * @throws ResourceException always; an instance of {@link NotSupportedException}s
     */
    @Override
    public RecordFactory getRecordFactory() throws ResourceException {
        throw new NotSupportedException();
    }


}
