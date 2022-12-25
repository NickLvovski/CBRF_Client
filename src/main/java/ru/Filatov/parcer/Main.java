package ru.Filatov.parcer;


import ru.Filatov.parcer.GUI.CurrExchangeGUI;
import ru.Filatov.parcer.convertation.CurrencyExchangeConverter;
import ru.Filatov.parcer.http.CentralBankOfRussiaClient;
import ru.Filatov.parcer.model.CurrencyExchange;
import ru.Filatov.parcer.repository.CurrencyExchangeRepositorySqlitempl;
import ru.Filatov.parcer.serialization.CsvSerializer;
import ru.Filatov.parcer.serialization.JsonSerializer;
import ru.Filatov.parcer.serialization.XmlSerializer;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        CurrencyExchangeConverter converter = CurrencyExchangeConverter.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        CentralBankOfRussiaClient cbr = CentralBankOfRussiaClient.getInstance();
        CsvSerializer csvSerializer = CsvSerializer.getInstance();
        JsonSerializer jsonSerializer = JsonSerializer.getInstance();
        XmlSerializer xmlSerializer = XmlSerializer.getInstance();
        Database database = Database.getInstance();
        CurrencyExchangeRepositorySqlitempl cers = new CurrencyExchangeRepositorySqlitempl(database);
        List<CurrencyExchange> currencies = converter.convert(cbr.getCurrencyExchange("26/12/2022"));
        for (CurrencyExchange currency : currencies)
            cers.insert(currency);

        CurrExchangeGUI gui = new CurrExchangeGUI();
        gui.setVisible(true);
    }
}