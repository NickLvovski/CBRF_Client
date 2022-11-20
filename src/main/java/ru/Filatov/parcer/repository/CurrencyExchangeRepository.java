package ru.Filatov.parcer.repository;

import ru.Filatov.parcer.model.CurrencyExchange;
import java.util.List;

public interface CurrencyExchangeRepository {
    CurrencyExchange findByid(Integer id);
    List<CurrencyExchange> findAll();
    List<CurrencyExchange> findAllByCode(String currencyCode);
    Integer delete(Integer id);
    void deleteAll();
    Integer insert(CurrencyExchange currency);
    Integer update(CurrencyExchange currency);
}
