/*
 * Copyright (C) 2014 Ralf Spöth
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
/**
 * Contains helper implementations for the implementation
 * of CCI compliance resource adapters.
 * 
 * <p>
 * Let {@code XiXi} be the name of an imaginary CCI-compliant 
 * resource adapter. We need to provide at least four types:
 * </p>
 * 
 * <em>Interface</em>
 * <ul>
 * <li>{@code XiXiConn} which must extend {@link javax.resource.cci.Connection} 
 * </li>
 * <li>{@code XiXiConnFactory} which must extend 
 *     {@link javax.resource.cci.ConnectionFactory}
 * </li>
 * </ul>
 * 
 * <em>Classes</em>
 * <ul>
 * <li>{@code XiXiConnImpl} which must implement {@code XiXiConn}
 * </li>
 * <li>{@code XiXiConnFactoryImpl} which must implement {@code XiXiConnFactory}
 *      and may extend {@link AbstractConnectionFactory}.
 * </li>
 * </ul>
 * 
 * 
 * @author Ralf Spöth
 * @version 1.0
 */
package de.spoeth.ra.cci;