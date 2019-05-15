package fr.univtlse3.m2dl.magnetrade.request;

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
public class RequestIT {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Request request;

    private Date date = new Date();

    private boolean alreadySetup = false;

    @Before
    public void setUp() {
        if (!alreadySetup) {
            alreadySetup = true;
            request = new Request("pic", "content", new Date(), new ArrayList<>());
            request = requestRepository.save(request);
        }
    }

    @Test
    @Rollback
    public void testCreateRequest() throws Exception {
        long oldCount = requestRepository.count();

        Request prop2 = new Request("picture", "text", date, new ArrayList<>());
        String propJson = objectMapper.writeValueAsString(prop2);

        mockMvc.perform(post("/api/request/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().isOk());

        assertThat(requestRepository.count(), IsEqual.equalTo(oldCount + 1));
    }

    @Test
    @Rollback
    public void testUpdateRequest() throws Exception {
        long oldCount = requestRepository.count();
        Long id = request.getId();

        request.setActive(false);
        request.setText("newContent");

        String propJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/request/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().isOk());

        assertThat(requestRepository.count(), IsEqual.equalTo(oldCount));
        assertEquals(request, requestRepository.findById(id).get());
        // assertThat(requestRepository.findById(id).get(), IsEqual.equalTo(request));
    }

    @Test
    @Rollback
    public void testFindById() throws Exception {
        Long id = request.getId();
        String propJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(get("/api/request/{id}", id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(propJson))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    public void testFindById_WrongId() throws Exception {
        Long id = -1L;

        mockMvc.perform(get("/api/request/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    @Rollback
    public void testFindAll() throws Exception {
        Request prop2 = new Request("picture", "text", date, new ArrayList<>());
        prop2 = requestRepository.save(prop2);

        Request prop3 = new Request("anotherpic", "anotherContent", date, new ArrayList<>());
        prop3 = requestRepository.save(prop3);

        List<Request> props = new ArrayList<>();
        props.add(request);
        props.add(prop2);
        props.add(prop3);

        String propJson = objectMapper.writeValueAsString(props);

        System.out.println(requestRepository.findAll());

        mockMvc.perform(get("/api/request/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(propJson))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    public void testFindAllActive() throws Exception {
        Request prop2 = new Request("picture", "text", date, new ArrayList<>());
        prop2.setActive(false);
        requestRepository.save(prop2);

        Request prop3 = new Request("anotherpic", "anotherContent", date, new ArrayList<>());
        prop3 = requestRepository.save(prop3);

        List<Request> props = new ArrayList<>();
        props.add(request);
        props.add(prop3);

        String propJson = objectMapper.writeValueAsString(props);

        mockMvc.perform(get("/api/request/active"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(propJson))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    public void testDeleteRequest() throws Exception {
        long oldCount = requestRepository.count();

        Long id = request.getId();

        mockMvc.perform(delete("/api/request/delete/{id}", id));

        assertThat(requestRepository.count(), IsEqual.equalTo(oldCount - 1));
        assertFalse(requestRepository.findById(id).isPresent());
    }
}
