import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.Filatov.parcer.convert.CurrencyExchangeConverter;
import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.http.CentralBankOfRussia;
import ru.Filatov.parcer.model.CurrencyExchange;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConverterTest {
    CentralBankOfRussia cbr = CentralBankOfRussia.getInstance();
    CurrencyExchangeConverter converter = CurrencyExchangeConverter.getInstance();
    DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    ValCurs valcurs = cbr.getCurrencyExchange("19/12/2002");
    List<CurrencyExchange> currencyExchanges = converter.convert(valcurs);
    @Test
    public void testFirst(){
        CurrencyExchange firstCurEx = currencyExchanges.get(0);
        Assert.assertNotNull(firstCurEx);
        Assert.assertNotNull(firstCurEx.getId());
        Assert.assertEquals((Integer) 1, firstCurEx.getId());
        Assert.assertNotNull(firstCurEx.getValue());
        Assert.assertEquals(18.0377, firstCurEx.getValue(), 0.0001);
        Assert.assertNotNull(firstCurEx.getNominal());
        Assert.assertEquals((Integer)1, firstCurEx.getNominal());
        Assert.assertNotNull(firstCurEx.getCurrencyName());
        Assert.assertEquals("Австралийский доллар", firstCurEx.getCurrencyName());
        Assert.assertNotNull(firstCurEx.getCurrencyCode());
        Assert.assertEquals("AUD", firstCurEx.getCurrencyCode());
        Assert.assertNotNull(firstCurEx.getDate());
        Assert.assertEquals(LocalDate.parse("19.12.2002", dt), firstCurEx.getDate());
    }

    @Test
    public void testSecond(){
        CurrencyExchange secondCurEx = currencyExchanges.get(1);
        Assert.assertNotNull(secondCurEx);
        Assert.assertNotNull(secondCurEx.getId());
        Assert.assertEquals((Integer) 2, secondCurEx.getId());
        Assert.assertNotNull(secondCurEx.getValue());
        Assert.assertEquals(50.8763, secondCurEx.getValue(), 0.0001);
        Assert.assertNotNull(secondCurEx.getNominal());
        Assert.assertEquals((Integer)1, secondCurEx.getNominal());
        Assert.assertNotNull(secondCurEx.getCurrencyName());
        Assert.assertEquals("Фунт стерлингов Соединенного королевства", secondCurEx.getCurrencyName());
        Assert.assertNotNull(secondCurEx.getCurrencyCode());
        Assert.assertEquals("GBP", secondCurEx.getCurrencyCode());
        Assert.assertNotNull(secondCurEx.getDate());
        Assert.assertEquals(LocalDate.parse("19.12.2002", dt), secondCurEx.getDate());
    }

    @Test
    public void testLast(){
        CurrencyExchange lastCurEx = currencyExchanges.get(currencyExchanges.size() - 1);
        Assert.assertNotNull(currencyExchanges);
        Assert.assertNotNull(lastCurEx);
        Assert.assertNotNull(lastCurEx.getId());
        Assert.assertEquals((Integer) currencyExchanges.size(), lastCurEx.getId());
        Assert.assertNotNull(lastCurEx.getValue());
        Assert.assertEquals(26.2568, lastCurEx.getValue(), 0.0001);
        Assert.assertNotNull(lastCurEx.getNominal());
        Assert.assertEquals((Integer) 100, lastCurEx.getNominal());
        Assert.assertNotNull(lastCurEx.getCurrencyName());
        Assert.assertEquals("Японских иен", lastCurEx.getCurrencyName());
        Assert.assertNotNull(lastCurEx.getCurrencyCode());
        Assert.assertEquals("JPY", lastCurEx.getCurrencyCode());
        Assert.assertNotNull(lastCurEx.getDate());
        Assert.assertEquals(LocalDate.parse("19.12.2002", dt), lastCurEx.getDate());
    }
}
