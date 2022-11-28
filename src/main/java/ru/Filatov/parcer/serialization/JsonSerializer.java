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

    @Override
    public Integer serialize(List<CurrencyExchange> currencies) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        Path filePath = Paths.get("src/main/resources/currencyExchange.json");

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
