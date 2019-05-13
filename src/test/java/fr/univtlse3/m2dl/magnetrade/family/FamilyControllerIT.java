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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FamilyControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FamilyRepository familyRepository;
    private Family family;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        family = new Family("Canada");
        family = familyRepository.save(family);
    }

    @Test
    public void testFindById() throws Exception {

        Long id = family.getId();
        String familyJson = objectMapper.writeValueAsString(family);

        this.mockMvc
                .perform(get("/api/family/read/{id}", id))
                .andExpect(status().isOk())
                //.andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().json(familyJson));

    }

    @Test
    public void testFindById_WrongId() throws Exception {
        Long id = -1L;

        mockMvc.perform(get("/api/family/read/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFamily() throws Exception {
        long oldCount = familyRepository.count();
        Family user2 =  new Family("Africa");
        String familyJson = objectMapper.writeValueAsString(user2);

        mockMvc.perform(post("/api/family/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(familyJson))
                .andExpect(status().is2xxSuccessful());
        Assert.assertThat(familyRepository.count(), IsEqual.equalTo(oldCount + 1));

        mockMvc.perform(get("/api/family/delete/{id}", user2.getId()));

        mockMvc.perform(post("/api/family/read/{id}", user2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(familyJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreateFamily() throws Exception {
        long oldCount = familyRepository.count();

        Family family2 =  new Family("Africa");
        String familyJson = objectMapper.writeValueAsString(family2);

        mockMvc.perform(post("/api/family/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(familyJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(familyRepository.count(), IsEqual.equalTo(oldCount + 1));
    }

    @Test
    public void testUpdateFamily() throws Exception{
        long oldCount = familyRepository.count();
        Long id = family.getId();

        family.setName("African America");
        //System.out.println(family.getName());

        String familyJson = objectMapper.writeValueAsString(family);

        mockMvc.perform(post("/api/family/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(familyJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(familyRepository.count(), IsEqual.equalTo(oldCount));
        Assert.assertThat(familyRepository.findById(id).get().getName(), IsEqual.equalTo(family.getName()));
    }
}
