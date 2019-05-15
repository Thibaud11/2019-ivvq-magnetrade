package fr.univtlse3.m2dl.magnetrade.proposal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProposalIT {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Proposal proposal;

    private Date date = new Date();

    private boolean alreadySetup = false;

    @Before
    public void setUp() {
        if (!alreadySetup) {
            alreadySetup = true;
            proposal = new Proposal("pic", "content", new Date(), new ArrayList<>());
            proposal = proposalRepository.save(proposal);
        }
    }

    @Test
    @Rollback
    public void testCreateProposal() throws Exception {
        long oldCount = proposalRepository.count();

        Proposal prop2 = new Proposal("picture", "text", date, new ArrayList<>());
        String propJson = objectMapper.writeValueAsString(prop2);

        mockMvc.perform(post("/api/proposal/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().isOk());

        assertThat(proposalRepository.count(), IsEqual.equalTo(oldCount + 1));
    }

    @Test
    @Rollback
    public void testUpdateProposal() throws Exception {
        long oldCount = proposalRepository.count();
        Long id = proposal.getId();

        proposal.setActive(false);
        proposal.setText("newContent");

        String propJson = objectMapper.writeValueAsString(proposal);

        mockMvc.perform(post("/api/proposal/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().isOk());

        assertThat(proposalRepository.count(), IsEqual.equalTo(oldCount));
        assertEquals(proposal, proposalRepository.findById(id).get());
        // assertThat(proposalRepository.findById(id).get(), IsEqual.equalTo(proposal));
    }

    @Test
    @Rollback
    public void testFindById() throws Exception {
        Long id = proposal.getId();
        String propJson = objectMapper.writeValueAsString(proposal);

        mockMvc.perform(get("/api/proposal/{id}", id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(propJson))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    public void testFindById_WrongId() throws Exception {
        Long id = -1L;

        mockMvc.perform(get("/api/proposal/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    @Rollback
    public void testFindAll() throws Exception {
        Proposal prop2 = new Proposal("picture", "text", date, new ArrayList<>());
        prop2 = proposalRepository.save(prop2);

        Proposal prop3 = new Proposal("anotherpic", "anotherContent", date, new ArrayList<>());
        prop3 = proposalRepository.save(prop3);

        List<Proposal> props = new ArrayList<>();
        props.add(proposal);
        props.add(prop2);
        props.add(prop3);

        String propJson = objectMapper.writeValueAsString(props);

        System.out.println(proposalRepository.findAll());

        mockMvc.perform(get("/api/proposal/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(propJson))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    public void testFindAllActive() throws Exception {
        Proposal prop2 = new Proposal("picture", "text", date, new ArrayList<>());
        prop2.setActive(false);
        proposalRepository.save(prop2);

        Proposal prop3 = new Proposal("anotherpic", "anotherContent", date, new ArrayList<>());
        prop3 = proposalRepository.save(prop3);

        List<Proposal> props = new ArrayList<>();
        props.add(proposal);
        props.add(prop3);

        String propJson = objectMapper.writeValueAsString(props);

        mockMvc.perform(get("/api/proposal/active"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(propJson))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    public void testDeleteProposal() throws Exception {
        long oldCount = proposalRepository.count();

        Long id = proposal.getId();

        mockMvc.perform(delete("/api/proposal/delete/{id}", id));

        assertThat(proposalRepository.count(), IsEqual.equalTo(oldCount - 1));
        assertFalse(proposalRepository.findById(id).isPresent());
    }
}
