import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.dto.Valute;
import ru.Filatov.parcer.util.XmlToValCursParser;

import java.text.SimpleDateFormat;
import java.util.List;

public class XmlToValCursParserTest {
    XmlToValCursParser parser = XmlToValCursParser.getInstance();
    SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");
    ValCurs valcurs = parser.parse("src/test/resources/XML_daily.xml");
    List<Valute> valuteList = valcurs.getValuteList();
    Valute firstValute = valuteList.get(0);
    Valute secondValute = valuteList.get(1);
    Valute lastValute = valuteList.get(valuteList.size() - 1);

    @Test
    public void testParseFirst() throws Exception{
        Assert.assertNotNull(valcurs);
        Assert.assertNotNull(valcurs.getName());
        Assert.assertEquals("Foreign Currency Market", valcurs.getName());
        Assert.assertNotNull(valcurs.getDate());
        Assert.assertEquals(dt.parse("19.12.2002"), valcurs.getDate());
        Assert.assertNotNull(valuteList);
        Assert.assertNotNull(firstValute);
        Assert.assertNotNull(firstValute.getId());
        Assert.assertEquals("R01010", firstValute.getId());
        Assert.assertNotNull(firstValute.getNumCode());
        Assert.assertEquals((Integer) 36, firstValute.getNumCode());
        Assert.assertNotNull(firstValute.getCharCode());
        Assert.assertEquals("AUD", firstValute.getCharCode());
        Assert.assertNotNull(firstValute.getNominal());
        Assert.assertEquals((Integer) 1, firstValute.getNominal());
        Assert.assertNotNull(firstValute.getName());
        Assert.assertEquals("Австралийский доллар", firstValute.getName());
        Assert.assertNotNull(firstValute.getValue());
        Assert.assertEquals(18.0377, firstValute.getValue(), 0.0001);
    }

    @Test
    public void testParseSecond(){
        Assert.assertNotNull(secondValute);
        Assert.assertNotNull(secondValute.getId());
        Assert.assertEquals("R01035", secondValute.getId());
        Assert.assertNotNull(secondValute.getNumCode());
        Assert.assertEquals((Integer) 826, secondValute.getNumCode());
        Assert.assertNotNull(secondValute.getCharCode());
        Assert.assertEquals("GBP", secondValute.getCharCode());
        Assert.assertNotNull(secondValute.getNominal());
        Assert.assertEquals((Integer) 1, secondValute.getNominal());
        Assert.assertNotNull(secondValute.getName());
        Assert.assertEquals("Фунт стерлингов Соединенного королевства", secondValute.getName());
        Assert.assertNotNull(secondValute.getValue());
        Assert.assertEquals(50.8763, secondValute.getValue(), 0.0001);
    }

    @Test
    public void testParseLast(){
        Assert.assertNotNull(lastValute);
        Assert.assertNotNull(lastValute.getId());
        Assert.assertEquals("R01820", lastValute.getId());
        Assert.assertNotNull(lastValute.getNumCode());
        Assert.assertEquals((Integer) 392, lastValute.getNumCode());
        Assert.assertNotNull(lastValute.getCharCode());
        Assert.assertEquals("JPY", lastValute.getCharCode());
        Assert.assertNotNull(lastValute.getNominal());
        Assert.assertEquals((Integer) 100, lastValute.getNominal());
        Assert.assertNotNull(lastValute.getName());
        Assert.assertEquals("Японских иен", lastValute.getName());
        Assert.assertNotNull(lastValute.getValue());
        Assert.assertEquals(26.2568, lastValute.getValue(), 0.0001);
    }
}
