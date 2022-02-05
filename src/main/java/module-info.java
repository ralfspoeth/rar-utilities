open module com.pd.spr.rar.utilities {
    requires java.naming;
    requires jakarta.jakartaee.api;
    requires java.transaction.xa;
    exports com.pd.spr.rar.cci;
    exports com.pd.spr.rar.ci;
    exports com.pd.spr.rar.outbound;
    exports com.pd.spr.rar.inbound;
    exports com.pd.spr.rar;
}