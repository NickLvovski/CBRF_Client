package ru.Filatov.parcer.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.Filatov.parcer.model.CurrencyExchange;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonSerializer implements SerializerService{
    private static JsonSerializer instance;

    private JsonSerializer() {}

    public static JsonSerializer getInstance(){
        if (instance == null){
            instance = new JsonSerializer();
        }
        return instance;
    }

    public Integer serialize(List<CurrencyExchange> currencies) {
        String uri = "src/main/resources/currencyExchange.json";

        return serialize(currencies, uri);
    }

    public Integer serialize(List<CurrencyExchange> currencies, String uri){
        Path filePath = Paths.get(uri);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        try {
            if (Files.exists(filePath)) Files.delete(filePath);
            Files.createFile(filePath);

            objectMapper.writeValue(filePath.toFile(), currencies);
            return 0;

        } catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }
}
