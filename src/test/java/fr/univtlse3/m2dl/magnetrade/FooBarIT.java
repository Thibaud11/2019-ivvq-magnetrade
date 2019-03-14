package fr.univtlse3.m2dl.magnetrade;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FooBarIT {

    @Test
    public void inteTest() {
        Logger.getAnonymousLogger().info("TEST INTE");
        assertThat("testIntegration", true);
    }

}
