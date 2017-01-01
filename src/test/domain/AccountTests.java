package domain;

import com.luxoft.currencytradeapp.dao.UserDao;
import com.luxoft.currencytradeapp.entity.Account;
import com.luxoft.currencytradeapp.entity.User;
import com.luxoft.currencytradeapp.exceptions.NotEnoughFundsException;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Khrishpens Viktor
 *         created Декабрь 25 2016
 */
public class AccountTests {
    User user = new User();
    @Before
    public void setUp() throws Exception {

        user.setLogin("UserTest");
        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        CurrencyUnit rur = CurrencyUnit.of("RUR");
        Money balanceRur = Money.of(rur,3000);
        Account accRur = new Account(balanceRur);
        user.getAccounts().add(accRur);

        CurrencyUnit usd = CurrencyUnit.of("USD");
        Money balanceUsd = Money.of(usd,2000);
        Account accUsd = new Account(balanceUsd);
        user.getAccounts().add(accUsd);

        CurrencyUnit eur = CurrencyUnit.of("EUR");
        Money balanceEur = Money.of(eur,1000);
        Account accEur = new Account(balanceEur);
        user.getAccounts().add(accEur);
    }

    @Test
    public void testGetUser(){
        StringBuilder sb = new StringBuilder();
        sb.append(user.getAccounts().get(0).getBalance().getAmount().toString());
        sb.append(user.getAccounts().get(0).getCurrencyCode());
        sb.append("\n");

        sb.append(user.getAccounts().get(1).getBalance().getAmount().toString());
        sb.append(user.getAccounts().get(1).getCurrencyCode());
        sb.append("\n");

        sb.append(user.getAccounts().get(2).getBalance().getAmount().toString());
        sb.append(user.getAccounts().get(2).getCurrencyCode());
        sb.append("\n");
        assertEquals("3000.00RUR\n" +
                "2000.00USD\n" +
                "1000.00EUR\n",sb.toString());
        System.out.println(sb.toString());
    }

    @Test public void testWithdraw(){
        CurrencyUnit rur = CurrencyUnit.of("RUR");
        Money wAmount = Money.of(rur,1000);
        try {
            user.getAccounts().get(0).withdraw(wAmount);
        }
        catch (NotEnoughFundsException nf){
            nf.printStackTrace();
        }
        assertEquals(user.getAccounts().get(0).getBalance(),Money.of(rur,2000));
    }

    @Test public void testDeposit(){
        CurrencyUnit usd = CurrencyUnit.of("USD");
        Money dAmount = Money.of(usd,1000);
        user.getAccounts().get(1).deposit(dAmount);
        assertEquals(user.getAccounts().get(1).getBalance(),Money.of(usd,3000));
    }
}
