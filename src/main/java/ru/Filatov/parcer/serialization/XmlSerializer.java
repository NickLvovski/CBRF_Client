package ru.Filatov.parcer.serialization;

public class XmlSerializer {
    private static XmlSerializer instance;

    private XmlSerializer() {}

    public static XmlSerializer getInstance(){
        if (instance == null){
            instance = new XmlSerializer();
        }
        return instance;
    }
}
