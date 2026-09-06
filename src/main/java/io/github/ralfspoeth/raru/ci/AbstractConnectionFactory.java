package io.github.ralfspoeth.raru.ci;

import javax.naming.Reference;

/**
 * This class serves as a skeletal class for
 * non-CCI connection factories.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public abstract class AbstractConnectionFactory implements ConnectionFactory {

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
