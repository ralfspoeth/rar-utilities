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
package com.pd.spr.rar.cci;

import com.pd.spr.rar.ci.AbstractConnection;

import javax.resource.cci.Connection;
import java.io.Closeable;

/**
 * This base class ensures that subclasses implement both
 * the {@link Connection} and {@link AutoCloseable} interfaces;
 * the latter being optional.
 * <p>
 * Implementing {@link Closeable} would have been sufficient; however,
 * the {@link AutoCloseable} allows for try-with-resources idioms.
 * <p>
 * The class builds upon the non-CCI version due to their generic
 * similarity.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractCCIConnection extends AbstractConnection implements Connection {
}