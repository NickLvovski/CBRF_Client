import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.Filatov.parcer.Database;
import ru.Filatov.parcer.convertation.CurrencyExchangeConverter;
import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.http.CentralBankOfRussiaClient;
import ru.Filatov.parcer.model.CurrencyExchange;
import ru.Filatov.parcer.repository.CurrencyExchangeRepositorySqlitempl;

import java.time.LocalDate;
import java.util.List;

public class CurrencyExchangeRepositoryTest {
    CentralBankOfRussiaClient cbr = CentralBankOfRussiaClient.getInstance();
    ValCurs valCurs = cbr.getCurrencyExchange("19/12/2002");
    Database db = Database.getInstance();
    CurrencyExchangeRepositorySqlitempl cers = new CurrencyExchangeRepositorySqlitempl(db);

    public void insertCurrenciesToDb(){
        List<CurrencyExchange> currencies = CurrencyExchangeConverter.getInstance().convert(valCurs);
        System.out.println(currencies.size());
        currencies.forEach(currencyExchange -> cers.insert(currencyExchange));
        System.out.println(cers.findAll().size());
    }
    public void testSelectFirst(){

        CurrencyExchange db_cur = cers.findById(1);

        Assert.assertNotNull(db_cur);
        Assert.assertNotNull(db_cur.getId());
        Assert.assertEquals((Integer) 1, db_cur.getId());
        Assert.assertNotNull(db_cur.getValue());
        Assert.assertEquals(18.0377, db_cur.getValue(), 0.0001);
        Assert.assertNotNull(db_cur.getNominal());
        Assert.assertEquals((Integer)1, db_cur.getNominal());
        Assert.assertNotNull(db_cur.getCurrencyName());
        Assert.assertEquals("Австралийский доллар", db_cur.getCurrencyName());
        Assert.assertNotNull(db_cur.getCurrencyCode());
        Assert.assertEquals("AUD", db_cur.getCurrencyCode());
        Assert.assertNotNull(db_cur.getDate());
        Assert.assertEquals(LocalDate.parse("2002-12-19"), db_cur.getDate());
    }

    @Test
    public void testSelectSecond(){
        insertCurrenciesToDb();
        CurrencyExchange db_cur = cers.findById(2);
        Assert.assertNotNull(db_cur);
        Assert.assertNotNull(db_cur.getId());
        Assert.assertEquals((Integer) 2, db_cur.getId());
        Assert.assertNotNull(db_cur.getValue());
        Assert.assertEquals(50.8763, db_cur.getValue(), 0.0001);
        Assert.assertNotNull(db_cur.getNominal());
        Assert.assertEquals((Integer)1, db_cur.getNominal());
        Assert.assertNotNull(db_cur.getCurrencyName());
        Assert.assertEquals("Фунт стерлингов Соединенного королевства", db_cur.getCurrencyName());
        Assert.assertNotNull(db_cur.getCurrencyCode());
        Assert.assertEquals("GBP", db_cur.getCurrencyCode());
        Assert.assertNotNull(db_cur.getDate());
        Assert.assertEquals(LocalDate.parse("2002-12-19"), db_cur.getDate());

    }
}
