package ru.Filatov.parcer.convertation;

import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.model.CurrencyExchange;

import java.util.List;

public interface CurrencyExchangeConverterService {
    List<CurrencyExchange> convert(ValCurs valCurs);
}
