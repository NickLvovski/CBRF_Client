package ru.Filatov.parcer.repository;

import ru.Filatov.parcer.Database;
import ru.Filatov.parcer.model.CurrencyExchange;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CurrencyExchangeRepositorySqlitempl implements CurrencyExchangeRepository {
    private Connection connection;

    public CurrencyExchangeRepositorySqlitempl(Database db){
        this.connection = db.getConnection();
    }

    @Override
    public CurrencyExchange findByid(Integer id) {
        CurrencyExchange currencyExchange = null;
        try(Statement stmt = connection.createStatement()) {
            String query = String.format("SELECT * FROM currency_exchange WHERE id LIKE '%d'", id);
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                currencyExchange = new CurrencyExchange();
                currencyExchange.setId(result.getInt("id"));
                currencyExchange.setValue(result.getDouble("currency_value"));
                currencyExchange.setNominal(result.getInt("currency_nominal"));
                currencyExchange.setCurrencyName(result.getString("currency_name"));
                currencyExchange.setCurrencyCode(result.getString("currency_code"));
                currencyExchange.setDate(LocalDate.parse(result.getString("currency_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currencyExchange;
    }

    @Override
    public Integer insert(CurrencyExchange currency) {
        try(Statement stmt = connection.createStatement()){
            String sql = "INSERT INTO currency_exchange(id, currency_value, currency_nominal," +
                    "currency_name, currency_code, currency_date) " +
                    "VALUES(" +
                    currency.getId().toString() + "," +
                    currency.getValue().toString() + "," +
                    currency.getNominal().toString() + "," +
                    "\"" + currency.getCurrencyName() + "\"" + "," +
                    "\"" + currency.getCurrencyCode() + "\"" + "," +
                    "\"" + currency.getDate().toString() + "\"" + ")";

            stmt.executeUpdate(sql);
            return 0;
        } catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<CurrencyExchange> findAll() {
        List<CurrencyExchange> currencyExchanges = new LinkedList<>();
        try(Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM currency_exchange";
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                CurrencyExchange currencyExchange = new CurrencyExchange();
                currencyExchange.setId(result.getInt("id"));
                currencyExchange.setValue(result.getDouble("currency_value"));
                currencyExchange.setNominal(result.getInt("currency_nominal"));
                currencyExchange.setCurrencyName(result.getString("currency_name"));
                currencyExchange.setCurrencyCode(result.getString("currency_code"));
                currencyExchange.setDate(LocalDate.parse(result.getString("currency_date")));

                currencyExchanges.add(currencyExchange);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currencyExchanges;
    }

    public List<CurrencyExchange> findAllByCode(String currencyCode) {
        List<CurrencyExchange> currencyExchanges = new LinkedList<>();
        try(Statement stmt = connection.createStatement()) {
            String query = String.format("SELECT * FROM currency_exchange WHERE currency_code LIKE '%s'", currencyCode);
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                CurrencyExchange currencyExchange = new CurrencyExchange();
                currencyExchange.setId(result.getInt("id"));
                currencyExchange.setValue(result.getDouble("currency_value"));
                currencyExchange.setNominal(result.getInt("currency_nominal"));
                currencyExchange.setCurrencyName(result.getString("currency_name"));
                currencyExchange.setCurrencyCode(result.getString("currency_code"));
                currencyExchange.setDate(LocalDate.parse(result.getString("currency_date")));

                currencyExchanges.add(currencyExchange);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currencyExchanges;
    }

    public Integer delete(Integer id) {
        try(Statement stmt = connection.createStatement()) {
            String sql = String.format("DELETE * FROM currency_exchange WHERE id LIKE '%d'", id);
            stmt.execute(sql);

            return 0;
        } catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public void deleteAll() {
        try(Statement stmt = connection.createStatement()) {
            String sql = "DELETE * FROM currency_exchange";
            stmt.execute(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Integer update(CurrencyExchange currency) {
        try(Statement stmt = connection.createStatement()){
            String sql = "UPDATE currency_exchange" +
                    "SET id=" + currency.getId().toString() + "," +
                    "currency_value=" + currency.getValue().toString() + "," +
                    "currency_nominal=" + currency.getNominal().toString() + "," +
                    "currency_name=" + "\"" + currency.getCurrencyName() + "\"" + "," +
                    "currency_code=" + "\"" + currency.getCurrencyCode() + "\"" + "," +
                    "currency_date=" + "\"" + currency.getDate().toString() + "\"" +
                    String.format("\nWHERE id LIKE '%d'", currency.getId());

            stmt.executeUpdate(sql);
            return 0;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
}
