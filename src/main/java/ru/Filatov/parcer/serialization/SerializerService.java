package ru.Filatov.parcer.serialization;

import ru.Filatov.parcer.model.CurrencyExchange;

import java.util.List;

public interface SerializerService {
    Integer serialize(List<CurrencyExchange> currencies);
}
