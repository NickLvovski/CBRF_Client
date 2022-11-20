package ru.Filatov.parcer.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ValCurs {
    private Date date;
    private String name;
    private List<Valute> valuteList;

    public Date getDate() {
        return date;
    }

    @JacksonXmlProperty(localName = "Date")
    public void setDate(String date) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");
        this.date = dt.parse(date);
    }

    public String getName() {
        return name;
    }

    @JacksonXmlProperty(localName = "name")
    public void setName(String name) {
        this.name = name;
    }

    public List<Valute> getValuteList() {
        return valuteList;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    public void setValuteList(List<Valute> valuteList) {
        this.valuteList = valuteList;
    }
}
