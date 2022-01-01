module org.postgresql.jdbc {
    requires java.management;

    requires transitive java.desktop;
    requires transitive java.logging;
    requires transitive java.naming;
    requires transitive java.security.jgss;
    requires transitive java.security.sasl;
    requires transitive java.sql;
    requires transitive java.transaction.xa;
    requires transitive java.xml;

    exports org.postgresql;
    exports org.postgresql.copy;
    exports org.postgresql.core;
    exports org.postgresql.core.v3;
    exports org.postgresql.core.v3.adaptivefetch;
    exports org.postgresql.core.v3.replication;
    exports org.postgresql.ds;
    exports org.postgresql.ds.common;
    exports org.postgresql.fastpath;
    exports org.postgresql.geometric;
    exports org.postgresql.gss;
    exports org.postgresql.hostchooser;
    exports org.postgresql.jdbc;
    exports org.postgresql.jdbc2;
    exports org.postgresql.jdbc2.optional;
    exports org.postgresql.jdbc3;
    exports org.postgresql.jre7.sasl;
    exports org.postgresql.largeobject;
    exports org.postgresql.osgi;
    exports org.postgresql.replication;
    exports org.postgresql.replication.fluent;
    exports org.postgresql.replication.fluent.logical;
    exports org.postgresql.replication.fluent.physical;
    exports org.postgresql.shaded.com.ongres.saslprep;
    exports org.postgresql.shaded.com.ongres.scram.client;
    exports org.postgresql.shaded.com.ongres.scram.common;
    exports org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64;
    exports org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2;
    exports org.postgresql.shaded.com.ongres.scram.common.exception;
    exports org.postgresql.shaded.com.ongres.scram.common.gssapi;
    exports org.postgresql.shaded.com.ongres.scram.common.message;
    exports org.postgresql.shaded.com.ongres.scram.common.stringprep;
    exports org.postgresql.shaded.com.ongres.scram.common.util;
    exports org.postgresql.shaded.com.ongres.stringprep;
    exports org.postgresql.ssl;
    exports org.postgresql.ssl.jdbc4;
    exports org.postgresql.sspi;
    exports org.postgresql.translation;
    exports org.postgresql.util;
    exports org.postgresql.util.internal;
    exports org.postgresql.xa;
    exports org.postgresql.xml;

    provides java.sql.Driver with
            org.postgresql.Driver;

}
