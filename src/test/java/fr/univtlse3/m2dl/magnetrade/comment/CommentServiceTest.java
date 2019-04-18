package fr.univtlse3.m2dl.magnetrade.comment;

import fr.univtlse3.m2dl.magnetrade.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    private Date today;

    private User obiwan;

    @Before
    public void setUp() {
        today = new Date();
        obiwan = new User("Obiwan", "Kenobi", "keke@jedi.gxy", today, "Ben", "123", "0625356837", "pouet");
    }

    @Test
    public void testCreateOrUpdateComment() {
        Comment commentToReturn = new Comment(false, today, "", obiwan);
        Comment commentToSave = new Comment(false, today, "", obiwan);
        when(commentRepository.save(any(Comment.class))).thenReturn(commentToReturn);
        assertEquals(commentToReturn, commentService.createOrUpdateComment(commentToSave));
        verify(commentRepository).save(any(Comment.class));
    }

}