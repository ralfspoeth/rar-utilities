package io.github.ralfspoeth.raru.ci;

import jakarta.resource.Referenceable;
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
