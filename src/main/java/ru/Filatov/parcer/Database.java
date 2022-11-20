package ru.Filatov.parcer;

import java.sql.Connection;

public class Database {
    private Connection connection;
    public static final Database INSTANCE = new Database();
    private Database(){
    }

    private void prepareDirectory(){}
    private void initDb(){}
    private void closeConnection() {}
}
