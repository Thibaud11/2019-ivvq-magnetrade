package fr.univtlse3.m2dl.magnetrade.family;


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
public class FamilyControllerIT {

    private static final String CONTENT_TYPE = "application/json";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FamilyRepository userRepository;
    private Family family;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        family = new Family("Canada");
        family = userRepository.save(family);
    }

    @Before
    public void setup () {
       /* FamilyController uc = new FamilyController();
        uc.setFamilyService(new FamilyService());
        this.mockMvc = MockMvcBuilders.standaloneSetup(uc).build(); */

    }

    @Test
    public void testFindById() throws Exception {

        Long id = family.getId();
        String userJson = objectMapper.writeValueAsString(family);

        this.mockMvc
                .perform(get("/api/family/read/{id}", id))
                .andExpect(status().isOk())
                //.andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().json(userJson));

    }

    @Test
    public void testFindById_WrongId() throws Exception {
        Long id = -1L;

        mockMvc.perform(get("/api/family/read/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFamily() throws Exception {
        long oldCount = userRepository.count();
        Family user2 =  new Family("Africa");
        String propJson = objectMapper.writeValueAsString(user2);

        mockMvc.perform(post("/api/family/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());
        Assert.assertThat(userRepository.count(), IsEqual.equalTo(oldCount + 1));

        mockMvc.perform(get("/api/family/delete/{id}", user2.getId()));

        mockMvc.perform(post("/api/family/read/{id}", user2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreateFamily() throws Exception {
        long oldCount = userRepository.count();

        Family user2 =  new Family("Africa");
        String propJson = objectMapper.writeValueAsString(user2);

        mockMvc.perform(post("/api/family/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(userRepository.count(), IsEqual.equalTo(oldCount + 1));
    }

    @Test
    public void testUpdateFamily() throws Exception{
        long oldCount = userRepository.count();
        Long id = family.getId();

        family.setName("African America");

        String propJson = objectMapper.writeValueAsString(family);

        mockMvc.perform(post("/api/family/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(userRepository.count(), IsEqual.equalTo(oldCount));
        Assert.assertThat(userRepository.findById(id).get(), IsEqual.equalTo(family));
    }
}
