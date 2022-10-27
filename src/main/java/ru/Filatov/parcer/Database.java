package ru.Filatov.parcer;

import okhttp3.Connection;

public class Database {
    private Connection connection;
    public static final Database INSTANCE = new Database();
    private Database(){
    }

    private void prepareDirectory(){}
    private void initDb(){}
    private void closeConnection() {}
}
