package ru.Filatov.parcer.serialization;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ru.Filatov.parcer.model.CurrencyExchange;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XmlSerializer implements SerializerService{
    private static XmlSerializer instance;

    private XmlSerializer() {}

    public static XmlSerializer getInstance(){
        if (instance == null){
            instance = new XmlSerializer();
        }
        return instance;
    }

    private static class CurrencyExchanges{
        // класс-обертка для более "красивой" записи в xml
        List<CurrencyExchange> currencyExchanges;

        public CurrencyExchanges(List<CurrencyExchange> currencyExchanges) {
            this.currencyExchanges = currencyExchanges;
        }

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "currencyExchange")
        public List<CurrencyExchange> getCurrencyExchanges() {
            return currencyExchanges;
        }
    }

    @Override
    public Integer serialize(List<CurrencyExchange> currencies) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();
        Path filePath = Paths.get("src/main/resources/currencyExchange.xml");

        try{
            if(Files.exists(filePath)) Files.delete(filePath);
            Files.createFile(filePath);

            xmlMapper.writeValue(filePath.toFile(), new CurrencyExchanges(currencies));
            return 0;

        } catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }
}
