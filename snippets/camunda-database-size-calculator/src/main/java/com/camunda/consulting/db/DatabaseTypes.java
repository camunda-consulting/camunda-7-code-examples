package com.camunda.consulting.db;

import lombok.Getter;

@Getter
public enum DatabaseTypes {

    POSTGRESQL("Postgresql"), MYSQL("MySQL"), ORACLE("Oracle"),
    MARIADB("MariaDB"), IBM_DB2("IBM DB2"), H2("H2"), MSSQL("Microsoft SQL Server");

    String dbName;

    DatabaseTypes(String dbName){
        this.dbName = dbName;
    }

}
