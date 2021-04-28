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
package com.pd.spr.rar;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnectionFactory;
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
