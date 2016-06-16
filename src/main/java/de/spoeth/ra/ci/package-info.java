/*
 * Copyright (C) 2015 Ralf Spöth
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
 * Utility classes for the implementation of non-CCI 
 * resource adapters.
 * 
 * There is no need to implement the interfaces or
 * extend the classes in this package, neither in general nor
 * specifically when utilizing this library.
 * The main purpose of the interfaces {@link Connection} and
 * {@link ConnectionFactory} is to easily comply with 
 * the requirements of the JCA.
 * 
 * <p>
 * You'll provide at least two interfaces and two classes
 * with an imaginary outbound resource adapter named {@code YourRA}:
 * </p>
 
 *   <em>Interfaces</em>
 *   <ul>
 *     <li>{@code YourConn}, which may extend {@link Connection} and 
 *         should provide business methods like - say - {@code void doIt()}
 *     </li>
 *     <li>{@code YourConnFactory}, which may extends {@link ConnectionFactory}
 *         and should provide a factory method with a signature that returns
 *         an instance of type {@code YourConn}, as in 
 *         {@code YourConn open();}
 *     </li>
 *   </ul>
 *   <em>Classes</em>
 *   <ul>
 *     <li>{@code YourConnImpl}, which must implement {@code YourConn}</li>
 *     <li>{@code YourConnFactoryImpl}, which must implement 
 *         {@code YourConnFactory} and may extend {@link AbstractConnectionFactory}
 *     </li>
 *   </ul>
 * 
 * <p>
 * The interfaces {@code YourConn} and {@code YourConnFactory} should be packaged
 * in their own Java archive ({@code .jar}-file). Both client code and the resource adapters
 * need to reference this archive.
 * </p>
 * 
 * The client-side looks like this:
 * <pre>
 * public class Client {
 * 
 *    {@code @Resource} YourConnFactory adapter;
 * 
 *     public void useIt() {
 *         try(YourConnn conn = adapter.open()) {
 *             conn.doIt();
 *         }
 *     }
 * } 
 * </pre>
 * 
 * 
 * @author Ralf Spöth
 * @version 1.0
 */
package de.spoeth.ra.ci;