package ru.Filatov.parcer.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.Filatov.parcer.dto.ValCurs;
import java.io.File;
import java.io.IOException;

public class XmlToValCursParser {
    private static final XmlToValCursParser INSTANCE = new XmlToValCursParser();
    private XmlToValCursParser(){}

    public static XmlToValCursParser getInstance(){
        return INSTANCE;
    }

    public ValCurs parse(String path){
        File file = new File(path);
        XmlMapper xmlMapper = new XmlMapper();
        try{
            ValCurs valCurs = xmlMapper.readerFor(ValCurs.class).readValue(file);
            return valCurs;
        }
        catch (IOException e){
            e.printStackTrace(System.out);
        }
        return null;
    }
}
