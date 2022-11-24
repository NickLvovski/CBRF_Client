package ru.Filatov.parcer.serialization;

public class JsonSerializer {
    private static JsonSerializer instance;

    private JsonSerializer() {}

    public static JsonSerializer getInstance(){
        if (instance == null){
            instance = new JsonSerializer();
        }
        return instance;
    }
}
