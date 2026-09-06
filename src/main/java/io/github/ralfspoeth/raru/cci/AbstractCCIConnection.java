package io.github.ralfspoeth.raru.cci;

import jakarta.resource.cci.Connection;
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
public abstract class AbstractCCIConnection implements Connection, AutoCloseable {
}
