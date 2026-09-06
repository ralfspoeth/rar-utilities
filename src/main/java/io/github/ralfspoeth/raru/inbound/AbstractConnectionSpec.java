package io.github.ralfspoeth.raru.inbound;

import jakarta.resource.cci.ConnectionSpec;
import java.io.Serializable;

/**
 * Base class for JavaBeans&trade; compliant implementations
 * of the interface {@link ConnectionSpec}.
 * <p>
 * Implementations typically look like this:
 * <pre>
 * public class MySpec extends AbstractConnectionSpec {
 * private String user;
 * public String getUser() {return user;}
 * public void setUser(String s) {
 * String old = user;
 * user = s;
 * changeSupport.firePropertyChange("user", old, user);
 * }
 * }
 * </pre>
 * <p>
 * Do not forget to fire property changes.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractConnectionSpec implements ConnectionSpec, Serializable {
}
