package fr.univtlse3.m2dl.magnetrade.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    private Transaction transaction;
    @Before
    public void setup() {
        transaction =  new Transaction(0.2, new Date(1995, 10, 29));

    }
    @Test
    public void testGetPrice() {
        assert (transaction.getPrice()==0.2);
    }
    @Test
    public void testSetPrice(){
        transaction.setPrice(0.3);
        assert (transaction.getPrice()==0.3);
    }
    @Test
    public void testSetId() {
        transaction.setId(987L);
        assert (transaction.getId() == 987L);
    }
    @Test
    public void testGetDate() {
        assert (transaction.getDate().equals(new Date(1995, 10, 29)));


    }
    @Test
    public void testSetDate() {
        transaction.setDate(new Date(1996, 10, 29));
        assert (transaction.getDate().equals(new Date(1996, 10, 29)));
    }
}
