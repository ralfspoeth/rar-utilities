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
package de.spoeth.rar.cci;

import javax.resource.NotSupportedException;
import javax.resource.Referenceable;
import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.RecordFactory;

/**
 * This class provides a default implementation 
 * for the base interface {@link Referenceable} 
 * of {@link ConnectionFactory}
 * 
 * 
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractConnectionFactory extends de.spoeth.rar.ci.AbstractConnectionFactory implements ConnectionFactory {
    
    /**
     * Calls {@link #getConnection(javax.resource.cci.ConnectionSpec)}
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
     * @throws ResourceException always; an instance of {@link NotSupportedException}s
     */
    @Override
    public RecordFactory getRecordFactory() throws ResourceException {
        throw new NotSupportedException();
    }
    
    
    
}
