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

import java.util.Objects;
import javax.resource.cci.ConnectionMetaData;

/**
 * This class provides a canonical
 * implementation of the interface
 * {@link ConnectionMetaData}; with three final fields for the
 * required meta data properties.
 * 
 * @author Ralf Spöth
 * @version 1.0
 */
public class DefaultConnectionMetaData implements ConnectionMetaData {
    
    private final String
            eisProductName,
            eisProductVersion,
            userName;

    public DefaultConnectionMetaData(String eisProductName, String eisProductVersion, String userName) {
        this.eisProductName = eisProductName;
        this.eisProductVersion = eisProductVersion;
        this.userName = userName;
    }

    @Override
    public String getEISProductName() {
        return eisProductName;
    }

    @Override
    public String getEISProductVersion() {
        return eisProductVersion;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DefaultConnectionMetaData) {
            DefaultConnectionMetaData md = (DefaultConnectionMetaData)obj;
            return 
                    Objects.equals(eisProductName, md.eisProductName) &&
                    Objects.equals(eisProductVersion, md.eisProductVersion) &&
                    Objects.equals(userName, md.userName);
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eisProductName, eisProductVersion, userName);
    }
}
