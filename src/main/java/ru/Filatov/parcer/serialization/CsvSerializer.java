package ru.Filatov.parcer.serialization;

import ru.Filatov.parcer.model.CurrencyExchange;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class CsvSerializer implements SerializerService{
    private static CsvSerializer instance;
    private static final String CSV_SEPARATOR = ",";

    private CsvSerializer() {
    }

    public static CsvSerializer getInstance(){
        if (instance == null){
            instance = new CsvSerializer();
        }
        return instance;
    }

    @Override
    public Integer serialize(List<CurrencyExchange> currencies){
        Path filePath = Paths.get("src/main/resources/currencyExchange.csv");

        try{
            if (!Files.exists(filePath)) Files.createFile(filePath);
            StringBuilder stringBuilder = new StringBuilder();
            String outputData;
            String header = "id,currency_value,currency_nominal,currency_name,currency_code,currency_date";

            Files.write(filePath, header.getBytes()); // запись заголовка в файл
            for(CurrencyExchange currency : currencies){
                // проходим по каждому элементу списка и добавляем его свойства в stringBuilder
                stringBuilder.append("\n");
                stringBuilder.append(currency.getId());
                stringBuilder.append(CSV_SEPARATOR);
                stringBuilder.append(currency.getValue());
                stringBuilder.append(CSV_SEPARATOR);
                stringBuilder.append(currency.getNominal());
                stringBuilder.append(CSV_SEPARATOR);
                stringBuilder.append(currency.getCurrencyName());
                stringBuilder.append(CSV_SEPARATOR);
                stringBuilder.append(currency.getCurrencyCode());
                stringBuilder.append(CSV_SEPARATOR);
                stringBuilder.append(currency.getDate().toString());
            }
            outputData = stringBuilder.toString();
            Files.write(filePath, outputData.getBytes(), StandardOpenOption.APPEND);
            return 0;

        } catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }
}
