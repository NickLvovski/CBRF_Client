package ru.Filatov.parcer.http;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;
import ru.Filatov.parcer.dto.ValCurs;

public class CentralBankOfRussia {
    private static final String BASE_URL = "https://www.cbr.ru/";
    private static CentralBankOfRussia instance;
    private static CentralBankOfRussiaService client;

    private CentralBankOfRussia(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setConverter(new JacksonConverter(new XmlMapper()))
                .build();
        client = restAdapter.create(CentralBankOfRussiaService.class);
    }

    public static CentralBankOfRussia getInstance() {
        if (instance == null) {
            instance = new CentralBankOfRussia();
        }
        return instance;
    }

    public ValCurs getCurrencyExchange(String date){
        return client.getCurrencyExchange(date);
    }
}
