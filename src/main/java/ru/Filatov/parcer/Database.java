package ru.Filatov.parcer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;
    private static Database instance;
    private Database(){
        prepareDirectory();
        initDb();
    }

    public Connection getConnection(){
        return connection;
    }
    public static Database getInstance(){
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }

    private void prepareDirectory(){
        Path testDb = Paths.get("src/main/resources/currencyExchange.db");
        if (!Files.exists(testDb)){
            try{
                Files.createFile(testDb);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void initDb(){
        try {
            String url = "jdbc:sqlite:src/main/resources/currencyExchange.db";
            connection = DriverManager.getConnection(url);
            initCurrencyExchangeTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void initCurrencyExchangeTable(){
        try(Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS currency_exchange");
            stmt.execute("CREATE TABLE currency_exchange(" +
                    "id INTEGER PRIMARY KEY," +
                    "currency_value FLOAT NOT NULL," +
                    "currency_nominal INTEGER NOT NULL," +
                    "currency_name VARCHAR(50) NOT NULL," +
                    "currency_code VARCHAR(3) NOT NULL," +
                    "currency_date VARCHAR(15) NOT NULL)");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void closeConnection() {
        try {
            if (connection != null){
                connection.close();
                instance = null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
