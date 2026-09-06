open module com.pd.spr.rar.utilities {
    requires java.naming;
    requires jakarta.resource;
    requires java.transaction.xa;
    exports io.github.ralfspoeth.raru.cci;
    exports io.github.ralfspoeth.raru.ci;
    exports io.github.ralfspoeth.raru.outbound;
    exports io.github.ralfspoeth.raru.inbound;
    exports io.github.ralfspoeth.raru;
}