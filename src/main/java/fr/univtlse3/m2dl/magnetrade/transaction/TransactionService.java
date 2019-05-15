package fr.univtlse3.m2dl.magnetrade.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Setter for property 'transactionRepository'.
     * @param transactionRepository value for 'transactionRepository'
     */
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Getter for property 'transactionRepository'.
     * @return value
     */
    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    /**
     * Method to create or update a transaction.
     * @param transaction transaction to create/update
     * @return the transaction created/updated
     */
    public Transaction createOrUpdateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    /**
     * Method to delete a transaction.
     * @param id id of the transaction to delete
     */
    public void deleteTransaction(long idtransaction) {
        transactionRepository.deleteById(idtransaction);
    }

    /**
     * Method to find a transaction.
     * @param idtransaction id of the transaction to find.
     * @return transaction find
     */
    public Optional<Transaction> findTransactionById(long idtransaction) {
        return transactionRepository.findById(idtransaction);
    }

    /**
     * Method to find all transactions.
     * @return all transactions
     */
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

}
