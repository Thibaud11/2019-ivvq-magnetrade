package fr.univtlse3.m2dl.magnetrade.comment;

import fr.univtlse3.m2dl.magnetrade.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {

    private Date d;
    private User u;

    @Before
    public void init() {
        this.d = new Date();
        this.u = new User();
    }

    @Test
    public void isEditedTest() {
        Comment c = new Comment(true, this.d, "", this.u);
        Assert.assertTrue(c.isEdited());
    }

    @Test
    public void setEditedTest() {
        Comment c = new Comment(false, this.d, "", this.u);
        c.setEdited(true);
        Assert.assertTrue(c.isEdited());
    }

    @Test
    public void getDateTest() {
        Comment c = new Comment(true, this.d, "", this.u);
        Assert.assertEquals(this.d, c.getDate());
    }

    @Test
    public void setDateTest() {
        Comment c = new Comment();
        c.setDate(this.d);
        Assert.assertEquals(this.d, c.getDate());
    }

    @Test
    public void getTextTest() {
        Comment c = new Comment(true, this.d, "HUGO", this.u);
        Assert.assertEquals("HUGO", c.getText());
    }

    @Test
    public void setTextTest() {
        Comment c = new Comment();
        c.setText("HUGO");
        Assert.assertEquals("HUGO", c.getText());
    }

    @Test
    public void getCommenterTest() {
        User u1 = new User();
        Comment c = new Comment(true, this.d, "", u1);
        Assert.assertEquals(u1, c.getCommenter());
    }

    @Test
    public void setCommenterTest() {
        User u1 = new User();
        Comment c = new Comment();
        c.setCommenter(u1);
        Assert.assertEquals(u1, c.getCommenter());
    }

    @Test
    public void equalsTest() {
        Comment c1 = new Comment(false, this.d, "COUCOU", null);
        Comment c2 = new Comment(false, this.d, "COUCOU", null);
        Comment c3 = new Comment(true, this.d, "COUCOU", null);

        Assert.assertEquals(c1, c2);
        Assert.assertNotEquals(c1, c3);
    }

}