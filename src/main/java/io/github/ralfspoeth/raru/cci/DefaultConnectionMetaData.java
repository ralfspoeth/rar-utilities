package io.github.ralfspoeth.raru.cci;

import jakarta.resource.cci.ConnectionMetaData;

import java.util.Objects;

/**
 * This class provides a canonical
 * implementation of the interface
 * {@link ConnectionMetaData}; with three final fields for the
 * required meta data properties.
 *
 * @author Ralf Spöth
 * @version 1.0
 */
public class DefaultConnectionMetaData implements ConnectionMetaData {

    private final String
            eisProductName,
            eisProductVersion,
            userName;

    public DefaultConnectionMetaData(String eisProductName, String eisProductVersion, String userName) {
        this.eisProductName = eisProductName;
        this.eisProductVersion = eisProductVersion;
        this.userName = userName;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DefaultConnectionMetaData md) {
            return
                    Objects.equals(eisProductName, md.eisProductName) &&
                            Objects.equals(eisProductVersion, md.eisProductVersion) &&
                            Objects.equals(userName, md.userName);
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eisProductName, eisProductVersion, userName);
    }
}
