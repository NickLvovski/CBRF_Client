package ru.Filatov.parcer.repository;

import ru.Filatov.parcer.model.CurrencyExchange;

import java.util.List;

public class CurrencyExchangeRepositorySqlitempl implements CurrencyExchangeRepository {

    @Override
    public CurrencyExchange findByid(Integer id) {
        return null;
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
