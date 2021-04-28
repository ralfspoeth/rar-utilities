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
package com.pd.spr.rar.outbound;

import com.pd.spr.rar.cci.DefaultConnectionMetaData;

import javax.resource.spi.ManagedConnectionMetaData;

/**
 * This class builds upon {@link DefaultConnectionMetaData} and just
 * adds the property {@link #maxConnections} to the inherited properties.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public class DefaultManagedConnectionMetaData extends DefaultConnectionMetaData implements ManagedConnectionMetaData {

    public DefaultManagedConnectionMetaData(String eisProductName, String eisProductVersion, String userName, int maxConnections) {
        super(eisProductName, eisProductVersion, userName);
        this.maxConnections = maxConnections;
    }

    private final int maxConnections;

    @Override
    public int getMaxConnections() {
        return maxConnections;
    }
}
