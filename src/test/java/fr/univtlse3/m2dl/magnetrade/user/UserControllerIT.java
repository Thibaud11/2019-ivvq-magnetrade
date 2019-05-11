package fr.univtlse3.m2dl.magnetrade.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private User user;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        user = new User();
        user = userRepository.save(user);
    }

    @Test
    public void testFindById() throws Exception {
        Long id = user.getId();
        String userJson = objectMapper.writeValueAsString(user);

        this.mockMvc
                .perform(get("/api/user/find/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(userJson));
    }

    @Test
    public void testFindById_WrongId() throws Exception {
        Long id = -1L;

        mockMvc.perform(get("/api/user/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUser() throws Exception {
        long oldCount = userRepository.count();
        User user2 =  new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dSDF", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
        String propJson = objectMapper.writeValueAsString(user2);

        mockMvc.perform(post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());
        Assert.assertThat(userRepository.count(), IsEqual.equalTo(oldCount + 1));

        mockMvc.perform(get("/api/user/delete/{id}", user2.getId()));

        mockMvc.perform(post("/api/user/{id}", user2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreateUser() throws Exception {
        long oldCount = userRepository.count();

        User user2 =  new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dSDF", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
        String propJson = objectMapper.writeValueAsString(user2);

        mockMvc.perform(post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(userRepository.count(), IsEqual.equalTo(oldCount + 1));
    }

    @Test
    public void testUpdateUser() throws Exception{
        long oldCount = userRepository.count();
        Long id = user.getId();

        user.setPicture("ooooo");

        String propJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/user/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(userRepository.count(), IsEqual.equalTo(oldCount));
        Assert.assertThat(userRepository.findById(id).get(), IsEqual.equalTo(user));
    }

}
