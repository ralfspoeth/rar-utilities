package io.github.ralfspoeth.raru.ci;

/**
 * Non-CCI connections should implement {@link AutoCloseable}.
 * This skeletal class ensures not to forget about this requirement.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractConnection implements AutoCloseable {
}
