package fr.univtlse3.m2dl.magnetrade.transaction;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private Transaction transaction;

    @Before
    public void setup() {
        transactionService = new TransactionService();
        transactionService.setTransactionRepository(transactionRepository);
        transaction = new Transaction(0.2, new Date(1995, 10, 29));
    }

    @Test
    public void testSaveFromCrudRepositoryIsInvokedWhenutransactionSaved() {
        //when: la méthode savemagnet est invoquée
        transactionService.createOrUpdateTransaction(transaction);
        // then: la méthode save du magnetRepository associé est invoquée
        verify(transactionService.getTransactionRepository()).save(transaction);
    }
    @Test
    public void testFindByIdFromCrudRepositoryIsInvokedWhentransactionIsFoundById() {
        //given: un transactionService
        //when: la méthode findtransactionById est invoquée
        transactionService.findTransactionById(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(transactionService.getTransactionRepository()).findById(0L);
    }

    @Test
    public void testGetTransactionRepository() {
        assert(transactionRepository.equals(transactionService.getTransactionRepository()));
    }

    @Test
    public void testSetTransactionRepository() {
        transactionService.setTransactionRepository(null);
        assertThat(transactionService.getTransactionRepository(), nullValue());
    }

    @Test
    public void testDeleteFromCrudRepositoryWhenDeletingTransaction(){
        //given: un transactionService
        //when: la méthode findtransactionById est invoquée
        transactionService.deleteTransaction(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(transactionService.getTransactionRepository()).deleteById(0L);
    }
}
