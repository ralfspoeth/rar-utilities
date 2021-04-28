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
package com.pd.spr.rar.ci;

import javax.naming.Reference;
import javax.resource.Referenceable;

/**
 * This class serves as a skeletal class for
 * non-CCI connection factories.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractConnectionFactory implements ConnectionFactory, Referenceable {

    protected Reference ref;

    @Override
    public void setReference(Reference rfrnc) {
        this.ref = rfrnc;
    }

    @Override
    public Reference getReference() {
        return ref;
    }

}
