package ru.Filatov.parcer.repository;

import ru.Filatov.parcer.Database;
import ru.Filatov.parcer.model.CurrencyExchange;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class CurrencyExchangeRepositorySqlitempl implements CurrencyExchangeRepository {
    private Database db;
    private Connection connection;

    public CurrencyExchangeRepositorySqlitempl(Database db){
        this.db = db;
        this.connection = db.getConnection();
    }

    @Override
    public CurrencyExchange findByid(Integer id) {
        CurrencyExchange currencyExchange = null;
        try {
            Statement stmt = connection.createStatement();
            String query = String.format(
                    "SELECT * FROM currency_exchange" +
                            "WHERE id LIKE %d", id);
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                currencyExchange = new CurrencyExchange();
                currencyExchange.setId(result.getInt("id"));
                currencyExchange.setValue(result.getDouble("currency_value"));
                currencyExchange.setNominal(result.getInt("currency_nominal"));
                currencyExchange.setCurrencyName(result.getString("currency_name"));
                currencyExchange.setCurrencyCode(result.getString("currency_code"));

                Date currencyDate = result.getDate("currency_date");
                LocalDate currencyLocalDate = currencyDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                currencyExchange.setDate(currencyLocalDate);
            }
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }

        return currencyExchange;
    }

    @Override
    public Integer insert(CurrencyExchange currency) {
        return null;
    }


    public List<CurrencyExchange> findAll() {
        return null;
    }

    public List<CurrencyExchange> findAllByCode(String currencyCode) {
        return null;
    }

    public Integer delete(Integer id) {
        return null;
    }

    public void deleteAll() {

    }

    public Integer update(CurrencyExchange currency) {
        return null;
    }
}
