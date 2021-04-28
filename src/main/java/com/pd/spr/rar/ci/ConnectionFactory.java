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

import javax.resource.Referenceable;
import java.io.Serializable;

/**
 * Implementing this interface ensures that non-CCI
 * connection implementations fulfill the general contracts
 * for connection factories, which requires to implement
 * both {@link Serializable} and {@link Referenceable}.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public interface ConnectionFactory extends Serializable, Referenceable {
}
