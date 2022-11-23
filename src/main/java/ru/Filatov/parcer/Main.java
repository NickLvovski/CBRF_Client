package ru.Filatov.parcer;


import ru.Filatov.parcer.convert.CurrencyExchangeConverter;
import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.http.CentralBankOfRussiaClient;
import ru.Filatov.parcer.model.CurrencyExchange;
import ru.Filatov.parcer.repository.CurrencyExchangeRepositorySqlitempl;


public class Main {
    public static void main(String[] args)
    {
        CentralBankOfRussiaClient cbr = CentralBankOfRussiaClient.getInstance();
        ValCurs valCurs = cbr.getCurrencyExchange("19/12/2002");
        CurrencyExchange cur = CurrencyExchangeConverter.getInstance().convert(valCurs).get(0);
        Database db = Database.getInstance();
        CurrencyExchange db_cur;
        CurrencyExchangeRepositorySqlitempl cers = new CurrencyExchangeRepositorySqlitempl(db);
        cers.insert(cur);
        db_cur = cers.findByid(1);
        db.closeConnection();
    }
}