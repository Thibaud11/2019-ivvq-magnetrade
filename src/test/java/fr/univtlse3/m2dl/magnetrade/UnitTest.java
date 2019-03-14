package fr.univtlse3.m2dl.magnetrade;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {

    @Test
    public void unitTest() {
        Logger.getAnonymousLogger().info("TEST UNIT");
        Assert.assertTrue(true);
    }

}
