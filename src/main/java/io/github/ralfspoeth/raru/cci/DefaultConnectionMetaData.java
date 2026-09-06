package io.github.ralfspoeth.raru.cci;

import jakarta.resource.cci.ConnectionMetaData;

/**
 * A record that implements {@link ConnectionMetaData}.
 */
public record DefaultConnectionMetaData(String eisProductName, String eisProductVersion, String userName)
        implements ConnectionMetaData
{
    @Override
    public String getEISProductName() {
        return eisProductName;
    }

    @Override
    public String getEISProductVersion() {
        return eisProductVersion;
    }

    @Override
    public String getUserName() {
        return userName;
    }
}
