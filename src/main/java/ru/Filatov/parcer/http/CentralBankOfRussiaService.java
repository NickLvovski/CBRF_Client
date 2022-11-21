package ru.Filatov.parcer.http;

import retrofit.http.GET;
import retrofit.http.Query;
import ru.Filatov.parcer.dto.ValCurs;

public interface CentralBankOfRussiaService {
    @GET("/scripts/XML_daily.asp")
    ValCurs getCurrencyExchange(@Query("date_req") String date);
}
