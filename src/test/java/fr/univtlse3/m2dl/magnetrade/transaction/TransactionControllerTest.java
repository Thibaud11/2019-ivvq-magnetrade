package fr.univtlse3.m2dl.magnetrade.transaction;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionControllerTest {
    private TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private Transaction transaction;

    @Before
    public void setup(){
        transactionController = new TransactionController();
        transactionController.setTransactionService(transactionService);
        transaction =  new Transaction(0.2, new Date(1995, 10, 29));
    }

    @Test
    public void testGetTransactionService(){
        assert(transactionService.equals(transactionController.getTransactionService()));
    }

    @Test
    public void testSetTransactionService() {
        transactionController.setTransactionService(null);
        assertThat(transactionService.getTransactionRepository(), nullValue());
    }

    @Test
    public void testCreateTransaction(){
        //when: la méthode createtransaction est invoquée
        transactionController.createtransaction(transaction);
        // then: la méthode savetransaction du transactionService associé est invoquée
        verify(transactionController.getTransactionService()).createOrUpdateTransaction(transaction);
    }

    @Test
    public void testDeleteTransaction(){
        //when: la méthode createtransaction est invoquée
        transactionController.deletetransaction(0L);
        // then: la méthode savetransaction du transactionService associé est invoquée
        verify(transactionController.getTransactionService()).deleteTransaction(0L);
    }

    @Test
    public void testFindTransactionById() throws Exception {
        try {
            //when: la méthode createtransaction est invoquée
            transactionController.findtransactionById(0L);
            // then: la méthode savetransaction du transactionService associé est invoquée
            verify(transactionController.getTransactionService()).findTransactionById(0L);
        } catch (ResponseStatusException ignored) {
            // Empty

    }
}}
