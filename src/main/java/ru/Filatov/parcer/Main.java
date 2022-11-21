package ru.Filatov.parcer;


import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.http.CentralBankOfRussia;
import ru.Filatov.parcer.http.CentralBankOfRussiaService;

public class Main {
    public static void main(String[] args)
    {
        CentralBankOfRussia cbr = CentralBankOfRussia.getInstance();
        ValCurs valCurs = cbr.getCurrencyExchange("19/12/2002");
        System.out.println(valCurs.getDate());
    }
}