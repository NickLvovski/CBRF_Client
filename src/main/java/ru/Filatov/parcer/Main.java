package ru.Filatov.parcer;


import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.http.CentralBankOfRussiaClient;

public class Main {
    public static void main(String[] args)
    {
        CentralBankOfRussiaClient cbr = CentralBankOfRussiaClient.getInstance();
        ValCurs valCurs = cbr.getCurrencyExchange("19/12/2002");
        System.out.println(valCurs.getDate());
        Database db = Database.getInstance();
        db.closeConnection();
    }
}