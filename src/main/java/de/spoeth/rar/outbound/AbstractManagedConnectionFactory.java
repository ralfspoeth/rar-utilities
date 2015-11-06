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

import de.spoeth.rar.DefaultConnectionManager;
import java.io.PrintWriter;
import java.util.Set;
import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionDefinition;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterAssociation;
import javax.security.auth.Subject;

/**
 * This class provides the boilerplate code for a 
 * {@link ManagedConnectionFactory} in an outbound resource adapter.
 * 
 * It uses the {@link DefaultConnectionManager} in non-managed environments.
 *  
 * Resource adapter implementations must implement the methods
 * {@link #createConnectionFactory(ConnectionManager)} and 
 * {@link #createManagedConnection(Subject, ConnectionRequestInfo)}, the former
 * providing an instance of the client-level connection factory and the 
 * latter providing an instance of the physical managed connection.
 *
 * This implementation does not support connection matching.
 * 
 * The actual implementation should use the {@link ConnectionDefinition}
 * annotation. It should furthermore comply with the JavaBeans
 * specification (No-arg constructor, properties with getters and setters,
 * must be serializable which it already is).
 * 
 * Remember to implement both {@link #equals(java.lang.Object)} and
 * {@link #hashCode()}.
 * 
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractManagedConnectionFactory implements 
        ManagedConnectionFactory, ResourceAdapterAssociation 
{

    /**
     * Feature is not supported; throws a {@link NotSupportedException}.
     * 
     * @param connectionSet
     * @param subject
     * @param cxRequestInfo
     * @return
     * @throws ResourceException 
     * @throws NotSupportedException always
     */
    @Override
    public ManagedConnection matchManagedConnections(
            Set connectionSet, Subject subject, 
            ConnectionRequestInfo cxRequestInfo) 
            throws ResourceException 
    {
        throw new NotSupportedException();
    }
    
    /**
     * 
     * @return
     * @throws ResourceException 
     */
    @Override
    public Object createConnectionFactory() throws ResourceException {
        return createConnectionFactory(DefaultConnectionManager.getInstance());
    }

    protected PrintWriter logWriter;
    
    @Override
    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.logWriter = out;
    }

    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return logWriter;
    }

    protected ResourceAdapter ra;
    
    @Override
    public ResourceAdapter getResourceAdapter() {
        return ra;
    }

    @Override
    public void setResourceAdapter(ResourceAdapter ra) throws ResourceException {
        if(this.ra!=null) 
            throw new ResourceException("resource adapter already set");
        this.ra = ra;
    }
}
