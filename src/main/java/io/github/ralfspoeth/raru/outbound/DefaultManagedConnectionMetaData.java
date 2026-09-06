package io.github.ralfspoeth.raru.outbound;

import io.github.ralfspoeth.raru.cci.DefaultConnectionMetaData;

import jakarta.resource.spi.ManagedConnectionMetaData;

/**
 * This class builds upon {@link DefaultConnectionMetaData} and just
 * adds the property {@link #maxConnections} to the inherited properties.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public class DefaultManagedConnectionMetaData extends DefaultConnectionMetaData implements ManagedConnectionMetaData {

    public DefaultManagedConnectionMetaData(String eisProductName, String eisProductVersion, String userName, int maxConnections) {
        super(eisProductName, eisProductVersion, userName);
        this.maxConnections = maxConnections;
    }

    private final int maxConnections;

    @Override
    public int getMaxConnections() {
        return maxConnections;
    }
}
