package ru.Filatov.parcer.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Valute {
    private String id;
    private Integer numCode;
    private String charCode;
    private Integer nominal;
    private String name;
    private Double value;

    public Valute(String id, Integer numCode, String charCode, Integer nominal, String name, Double value) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public Valute() {
    }

    public String getId() {
        return id;
    }

    @JacksonXmlProperty(localName = "ID")
    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumCode() {
        return numCode;
    }

    @JacksonXmlProperty(localName = "NumCode")
    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    @JacksonXmlProperty(localName = "CharCode")
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    @JacksonXmlProperty(localName = "Nominal")
    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    @JacksonXmlProperty(localName = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @JacksonXmlProperty(localName = "Value")
    public void setValue(String value){
        this.value = Double.valueOf(value.replace(",", "."));
    }
}
