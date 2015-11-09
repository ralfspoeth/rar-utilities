/*
 * Copyright (C) 2015 SPR
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
package de.spoeth.rar.inbound;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.resource.cci.ConnectionSpec;

/**
 * Base class for JavaBeans&trade; compliant implementations
 * of the interface {@link ConnectionSpec}.
 * 
 * Implementations typically look like this:
 * <pre>
 public class MySpec extends AbstractConnectionSpec {
     private String user;
     public String getUser() {return user;}
     public void setUser(String s) {
         String old = user;
         user = s;
         changeSupport.firePropertyChange("user", old, user);
     }
 }
 </pre>
 *
 * Do not forget to fire property changes.
 * 
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractConnectionSpec implements ConnectionSpec, Serializable {
    protected final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(propertyName, listener);
    }    
}
