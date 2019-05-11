package fr.univtlse3.m2dl.magnetrade.magnet;

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
public class MagnetControllerIT {

    private static final String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MagnetRepository magnetRepository;

    private Magnet magnet;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        magnet = new Magnet(MagnetTest.MAGNET_NAME, MagnetTest.MAGNET_PICTURE_URL, MagnetTest.MAGNET_DESCRIPTION, null);
        magnet = magnetRepository.save(magnet);
    }

    @Test
    public void testFindById() throws Exception {

        Long id = magnet.getId();
        String magnetJson = objectMapper.writeValueAsString(magnet);

        this.mockMvc
                .perform(get("/api/magnet/read/{id}", id))
                .andExpect(status().isOk())
                //.andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().json(magnetJson));

    }

    @Test
    public void testFindById_WrongId() throws Exception {
        Long id = -1L;

        mockMvc.perform(get("/api/magnet/read/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteMagnet() throws Exception {
        long oldCount = magnetRepository.count();
        Magnet magnet2 =  new Magnet(MagnetTest.NEW_MAGNET_NAME, MagnetTest.NEW_MAGNET_PICTURE_URL, MagnetTest.NEW_MAGNET_DESCRIPTION, null);
        String propJson = objectMapper.writeValueAsString(magnet2);

        mockMvc.perform(post("/api/magnet/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());
        Assert.assertThat(magnetRepository.count(), IsEqual.equalTo(oldCount + 1));

        mockMvc.perform(get("/api/magnet/delete/{id}", magnet2.getId()));

        mockMvc.perform(post("/api/magnet/read/{id}", magnet2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreateMagnet() throws Exception {
        long oldCount = magnetRepository.count();


        Magnet magnet2 =  new Magnet(MagnetTest.NEW_MAGNET_NAME, MagnetTest.NEW_MAGNET_PICTURE_URL, MagnetTest.NEW_MAGNET_DESCRIPTION, null);
        String propJson = objectMapper.writeValueAsString(magnet2);

        mockMvc.perform(post("/api/magnet/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(magnetRepository.count(), IsEqual.equalTo(oldCount + 1));
    }

    @Test
    public void testUpdateMagnet() throws Exception{
        long oldCount = magnetRepository.count();
        Long id = magnet.getId();

        magnet.setName(MagnetTest.NEW_MAGNET_NAME);

        String propJson = objectMapper.writeValueAsString(magnet);

        mockMvc.perform(post("/api/magnet/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propJson))
                .andExpect(status().is2xxSuccessful());

        Assert.assertThat(magnetRepository.count(), IsEqual.equalTo(oldCount));
        Assert.assertThat(magnetRepository.findById(id).get(), IsEqual.equalTo(magnet));
    }

}
