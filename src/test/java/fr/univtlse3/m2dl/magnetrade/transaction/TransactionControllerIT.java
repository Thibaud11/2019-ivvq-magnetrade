package fr.univtlse3.m2dl.magnetrade.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TransactionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    private Transaction transaction;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        transaction = new Transaction(0.2, new Date(1995, 10, 29));
        transaction = transactionRepository.save(transaction);
    }

    @Test
    public void testFindById() throws Exception {
        Long id = transaction.getId();
        String transactionJson = objectMapper.writeValueAsString(transaction);

        this.mockMvc
                .perform(get("/api/transaction/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(transactionJson));
    }

    @Test
    public void testFindById_WrongId() throws Exception {
        Long id = -1L;

        mockMvc.perform(get("/api/transaction/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletetransaction() throws Exception {
        long oldCount = transactionRepository.count();
        Transaction transaction2 =  new Transaction(0.2, new Date(1998, 10, 29));
        String propJson = objectMapper.writeValueAsString(transaction2);

        mockMvc.perform(post("/api/transaction/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());
        Assert.assertThat(transactionRepository.count(), IsEqual.equalTo(oldCount + 1));

        mockMvc.perform(get("/api/transaction/delete/{id}", transaction2.getId()));

        mockMvc.perform(post("/api/transaction/{id}", transaction2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreatetransaction() throws Exception {
        long oldCount = transactionRepository.count();

        Transaction transaction2 =  new Transaction(0.2, new Date(1999, 10, 29));
        String propJson = objectMapper.writeValueAsString(transaction2);

        mockMvc.perform(post("/api/transaction/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(transactionRepository.count(), IsEqual.equalTo(oldCount + 1));
    }

    @Test
    public void testUpdatetransaction() throws Exception{
        long oldCount = transactionRepository.count();
        Long id = transaction.getId();

        transaction.setPrice(9.6);

        String propJson = objectMapper.writeValueAsString(transaction);

        mockMvc.perform(post("/api/transaction/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(transactionRepository.count(), IsEqual.equalTo(oldCount));
        Assert.assertThat(transactionRepository.findById(id).get(), IsEqual.equalTo(transaction));
    }

}
