package io.github.ralfspoeth.raru;

import jakarta.resource.spi.ActivationSpec;
import jakarta.resource.spi.BootstrapContext;
import jakarta.resource.spi.Connector;
import jakarta.resource.spi.ResourceAdapter;
import javax.transaction.xa.XAResource;
import java.io.Serializable;

/**
 * This class may be useful for the development of resource adapters by
 * abstractly implementing the {@link ResourceAdapter} and, in order to comply
 * with the JavaBeans specification, {@link Serializable}.
 * <p>
 * The application server instantiates a single instance of this class and calls
 * the method {@link #start(jakarta.resource.spi.BootstrapContext)} afterwards.
 * The server invokes {@link #stop()} upon un-deployment of the resource adapter
 * and before it shuts down.
 * <p>
 * You may define JavaBeans properties and annotate them with the
 * {@link jakarta.resource.spi.ConfigProperty} annotation in order
 * to to provide the chance to
 * configure the resource adapter before deployment.
 * <p>
 * Do not forget to add the {@link Connector} annotation with your
 * implementation. Furthermore, add an implementation for both
 * {@link #equals(java.lang.Object)} and {@link #hashCode() }.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractResourceAdapter implements ResourceAdapter, Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    /**
     * A reference to the {@link BootstrapContext context} passed to the
     * {@link #start(jakarta.resource.spi.BootstrapContext)} method;
     * subclass-visible.
     * <p>
     * Note that this class does not provide get/set methods for the context.
     */
    protected transient BootstrapContext context;

    /**
     * Empty default implementation.
     *
     * @param ctx the bootstrap context
     */
    @Override
    public void start(BootstrapContext ctx) {
        this.context = ctx;
    }

    /**
     * Set {@link #context} to {@code null}.
     */
    @Override
    public void stop() {
        this.context = null;
    }

    /**
     * Default implementation returns {@code null} as it assumes no support for
     * XA transactions.
     *
     * @param specs array of activation specs
     * @return {@code null}
     */
    @Override
    public XAResource[] getXAResources(ActivationSpec[] specs) {
        return null;
    }
}
