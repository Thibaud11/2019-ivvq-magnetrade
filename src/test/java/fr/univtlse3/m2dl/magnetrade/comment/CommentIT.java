package fr.univtlse3.m2dl.magnetrade.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtlse3.m2dl.magnetrade.user.User;
import fr.univtlse3.m2dl.magnetrade.user.UserRepository;
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
public class CommentIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    private Comment comment;

    private User user;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        user = new User("obiwan", "kenobi", "hello.there@highground.fr", null, "ben", "jedi1234", "0624356189", "t");
        userRepository.save(user);
        comment = new Comment(false, new Date(), "coucou", user);
        commentRepository.save(comment);
    }

    @Test
    public void testCreateComment() {
        long oldCount = commentRepository.count();
        Comment newComment = new Comment(false, new Date(), "coucou", user);
        try {
            String propJson = objectMapper.writeValueAsString(newComment);
            mockMvc.perform(post("/api/comment/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(propJson))
                    .andExpect(status().is2xxSuccessful());

            Assert.assertThat(commentRepository.count(), IsEqual.equalTo(oldCount + 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateComment() {
        long oldCount = commentRepository.count();
        long id = comment.getId();
        comment.setText("bfjhjdjfdjfdjfk");

        try {
            String propJson = objectMapper.writeValueAsString(comment);
            mockMvc.perform(post("/api/comment/edit")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(propJson))
                    .andExpect(status().is2xxSuccessful());

            Assert.assertThat(commentRepository.count(), IsEqual.equalTo(oldCount + 1));
            Assert.assertThat(commentRepository.findById(id), IsEqual.equalTo(comment));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteComment() {
        long oldCount = commentRepository.count();
        Comment newComment = new Comment(false, new Date(), "coucou", user);
        commentRepository.save(newComment);

        try {
            String propJson = objectMapper.writeValueAsString(comment);
            mockMvc.perform(get("/api/magnet/delete/{id}", newComment.getId()));

            mockMvc.perform(post("/api/comment/find/{id}", newComment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(propJson))
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById() {
        long id = comment.getId();

        try {
            String commentJson = objectMapper.writeValueAsString(comment);
            this.mockMvc
                    .perform(get("/api/comment/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(content().json(commentJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById_WrongId() {
        try {
            long id = -1L;
            this.mockMvc
                    .perform(get("/api/comment/{id}", id))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
