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
package de.spoeth.rar;

import java.io.Serializable;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ConfigProperty;
import javax.resource.spi.Connector;
import javax.resource.spi.ResourceAdapter;
import javax.transaction.xa.XAResource;

/**
 * This class may be useful for the development of resource
 * adapters by abstractly implementing the {@link ResourceAdapter} 
 * and, in order to comply with the JavaBeans specification, 
 * {@link Serializable}.
 * 
 * The application server instantiates a single instance 
 * of this class and calls the method
 * {@link #start(javax.resource.spi.BootstrapContext)} afterwards.
 * The server invokes {@link #stop()} upon un-deployment of the resource
 * adapter and before it shuts down.
 * 
 * You may define JavaBeans properties and annotate them
 * with the {@link ConfigProperty} annotation in order to
 * to provide the chance to configure the resource adapter 
 * before deployment. This implementation provides a default
 * property {@link #mode}.
 * 
 * Do not forget to add the {@link Connector} annotation with your 
 * implementation. Furthermore, add an implementation for both
 * {@link #equals(java.lang.Object)} and {@link #hashCode() }.
 * 
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractResourceAdapter implements ResourceAdapter, Serializable {
    
    /**
     * A reference to the {@link BootstrapContext context} 
     * passed to the {@link #start(javax.resource.spi.BootstrapContext)} method;
     * subclass-visible.
     * 
     * Note that this class does not provide get/set
     * methods for the context.
     */
    protected BootstrapContext context;
    
    /**
     * Empty default implementation.
     * 
     * @param ctx
     */
    @Override
    public void start(BootstrapContext ctx) {
        this.context = ctx;
    }
    
    
    /**
     * Empty default implementation.
     */
    @Override
    public void stop() {        
        this.context = null;
    }
    
    /**
     * Default implementation returns {@code null} as it assumes
     * no support for XA transactions.
     * 
     * @param specs 
     * @return {@code null}
     */
    @Override
    public XAResource[] getXAResources(ActivationSpec[] specs) {
        return null;
    }
}
