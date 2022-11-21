package ru.Filatov.parcer.convert;

import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.dto.Valute;
import ru.Filatov.parcer.model.CurrencyExchange;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

public class CurrencyExchangeConverter {
    private static CurrencyExchangeConverter instance;

    private CurrencyExchangeConverter(){}

    public static CurrencyExchangeConverter getInstance(){
        if (instance == null){
            instance = new CurrencyExchangeConverter();
        }
        return instance;
    }

    public List<CurrencyExchange> convert(ValCurs valCurs){
        List<Valute> valuteList = valCurs.getValuteList();
        List<CurrencyExchange> result = new LinkedList<>();
        LocalDate valCursDate = valCurs.getDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        for(int i = 0; i < valuteList.size(); i++){
            Valute valute = valuteList.get(i);
            CurrencyExchange currencyExchange = new CurrencyExchange();
            currencyExchange.setId(i + 1);
            currencyExchange.setValue(valute.getValue());
            currencyExchange.setNominal(valute.getNominal());
            currencyExchange.setCurrencyName(valute.getName());
            currencyExchange.setCurrencyCode(valute.getCharCode());
            currencyExchange.setDate(valCursDate);
            result.add(currencyExchange);
        }

        return result;
    }
}
