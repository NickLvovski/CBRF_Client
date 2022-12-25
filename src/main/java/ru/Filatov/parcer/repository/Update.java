package ru.Filatov.parcer.repository;

import ru.Filatov.parcer.convertation.CurrencyExchangeConverter;
import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.http.CentralBankOfRussiaClient;
import ru.Filatov.parcer.model.CurrencyExchange;

import java.util.List;

public class Update {
    CurrencyExchangeRepositorySqlitempl cers;

    public Update(CurrencyExchangeRepositorySqlitempl cers){
        this.cers = cers;
    }

    public void UpdateDB(String date){
        CurrencyExchangeConverter converter = CurrencyExchangeConverter.getInstance();
        CentralBankOfRussiaClient cbrf = CentralBankOfRussiaClient.getInstance();
        ValCurs valCurs = cbrf.getCurrencyExchange(date);
        List<CurrencyExchange> currencies = converter.convert(valCurs);
        for (CurrencyExchange currency : currencies)
            cers.update(currency);
    }
}
