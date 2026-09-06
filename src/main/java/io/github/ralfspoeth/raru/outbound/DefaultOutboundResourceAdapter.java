package io.github.ralfspoeth.raru.outbound;

import io.github.ralfspoeth.raru.AbstractResourceAdapter;

import jakarta.resource.NotSupportedException;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.ActivationSpec;
import jakarta.resource.spi.ResourceAdapter;
import jakarta.resource.spi.endpoint.MessageEndpointFactory;

/**
 * Default implementation of the {@link ResourceAdapter} interface
 * for an outbound resource adapter.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public class DefaultOutboundResourceAdapter extends AbstractResourceAdapter {

    /**
     * Not implemented for outbound resource adapters.
     *
     * @param endpointFactory the endpoint factory
     * @param spec            the activation spec
     * @throws jakarta.resource.NotSupportedException always
     */
    @Override
    public void endpointActivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) throws ResourceException {
        throw new NotSupportedException();
    }

    /**
     * Empty implementation (does nothing).
     * <p>
     * {@link ResourceAdapter#endpointDeactivation(jakarta.resource.spi.endpoint.MessageEndpointFactory, jakarta.resource.spi.ActivationSpec)}
     * does not throw a {@link ResourceException}, so we may unfortunately not
     * throw a {@link NotSupportedException} instead.
     *
     * @param endpointFactory ignored
     * @param spec            ignored
     */
    @Override
    public void endpointDeactivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) {
        // empty
    }

}
