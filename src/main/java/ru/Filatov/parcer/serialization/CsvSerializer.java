package ru.Filatov.parcer.serialization;

import ru.Filatov.parcer.model.CurrencyExchange;

import java.util.List;

public class CsvSerializer {
    private static CsvSerializer instance;

    private CsvSerializer() {
    }

    public static CsvSerializer getInstance(){
        if (instance == null){
            instance = new CsvSerializer();
        }
        return instance;
    }

    public void serialize(List<CurrencyExchange> currencies){

    }
}
