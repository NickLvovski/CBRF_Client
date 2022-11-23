import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.Filatov.parcer.Database;
import ru.Filatov.parcer.convert.CurrencyExchangeConverter;
import ru.Filatov.parcer.dto.ValCurs;
import ru.Filatov.parcer.http.CentralBankOfRussiaClient;
import ru.Filatov.parcer.model.CurrencyExchange;
import ru.Filatov.parcer.repository.CurrencyExchangeRepositorySqlitempl;

import java.time.LocalDate;

public class CurrencyExchangeRepositoryTest {

    @Test
    public void testParseFirst() throws Exception{
        CentralBankOfRussiaClient cbr = CentralBankOfRussiaClient.getInstance();
        ValCurs valCurs = cbr.getCurrencyExchange("19/12/2002");
        CurrencyExchange cur = CurrencyExchangeConverter.getInstance().convert(valCurs).get(0);
        Database db = Database.getInstance();
        CurrencyExchange db_cur;
        CurrencyExchangeRepositorySqlitempl cers = new CurrencyExchangeRepositorySqlitempl(db);
        cers.insert(cur);
        db_cur = cers.findByid(1);
        db.closeConnection();

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
}
