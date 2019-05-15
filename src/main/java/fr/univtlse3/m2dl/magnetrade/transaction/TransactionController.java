package fr.univtlse3.m2dl.magnetrade.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Method to create or update a transaction.
     * @param transaction transaction to create/update
     * @return transaction created/updated
     */
    @PostMapping({"/create", "/edit"})
    public Transaction createtransaction(@RequestBody Transaction transaction) {
        return transactionService.createOrUpdateTransaction(transaction);
    }

    /**
     * Method to delete a transaction.
     * @param id id of the transaction to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deletetransaction(@PathVariable long id) {
        transactionService.deleteTransaction(id);
    }

    /**
     * Method to find all transactions.
     * @return all transactions
     */
    @GetMapping("/all")
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    /**
     * Method to find a transaction.
     * @param id id of the transaction to find
     * @return
     */
    @GetMapping("/{id}")
    public Transaction findtransactionById(@PathVariable("id") Long id) {
        return transactionService.findTransactionById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public TransactionService getTransactionService(){
        return transactionService;
    }

    public void setTransactionService(TransactionService transactionService){
        this.transactionService = transactionService;
    }

}
