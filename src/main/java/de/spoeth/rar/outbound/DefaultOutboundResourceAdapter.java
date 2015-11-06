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
package de.spoeth.rar.outbound;

import de.spoeth.rar.AbstractResourceAdapter;
import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.endpoint.MessageEndpointFactory;

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
     * @param endpointFactory
     * @param spec
     * @throws javax.resource.ResourceException
     * @throws UnsupportedOperationException always
     */
    @Override
    public void endpointActivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) throws ResourceException {
        throw new NotSupportedException();
    }

    /**
     * Empty implementation (does nothing).
     * 
     * {@link ResourceAdapter#endpointDeactivation(javax.resource.spi.endpoint.MessageEndpointFactory, javax.resource.spi.ActivationSpec)}
     * does not throw a {@link ResourceException}, so we may unfortunately not
     * throw a {@link NotSupportedException} instead.
     *
     * @param endpointFactory ignored
     * @param spec ignored
     */
    @Override
    public void endpointDeactivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) {
        // empty
    }

}
