package io.github.ralfspoeth.raru.outbound;

import jakarta.resource.ResourceException;
import jakarta.resource.cci.ConnectionMetaData;
import jakarta.resource.spi.ManagedConnectionMetaData;

/**
 * A record representing {@link DefaultManagedConnectionMetaData} objects.
 */
public record DefaultManagedConnectionMetaData(ConnectionMetaData connectionMetaData, int maxConnections)
        implements ManagedConnectionMetaData
{

    @Override
    public String getEISProductName() throws ResourceException {
        return connectionMetaData.getEISProductName();
    }

    @Override
    public String getEISProductVersion() throws ResourceException {
        return connectionMetaData.getEISProductVersion();
    }

    @Override
    public int getMaxConnections() {
        return maxConnections;
    }

    @Override
    public String getUserName() throws ResourceException {
        return connectionMetaData.getUserName();
    }
}
