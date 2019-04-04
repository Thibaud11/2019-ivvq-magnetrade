package fr.univtlse3.m2dl.magnetrade.request;

import fr.univtlse3.m2dl.magnetrade.comment.Comment;
import fr.univtlse3.m2dl.magnetrade.magnet.Magnet;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class RequestTest {

    private Date nowDate;
    private Request request;

    @Before
    public void setUp() throws Exception {
        nowDate = new Date();
        request = new Request(1L, true, "pic", "content", nowDate, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void testConstructor() throws Exception {
        Request request1 = new Request("pic", "content", nowDate, new ArrayList<>());
        assertThat(request1, IsEqual.equalTo(request));
    }

    @Test
    public void testGetId() throws Exception {
        assertThat(request.getId(), IsEqual.equalTo(1L));
    }

    @Test
    public void testSetId() throws Exception {
        request.setId(2L);
        assertThat(request.getId(), IsEqual.equalTo(2L));
    }

    @Test
    public void testGetActive() throws Exception {
        assertThat(request.getActive(), IsEqual.equalTo(true));
    }

    @Test
    public void testSetActive() throws Exception {
        request.setActive(false);
        assertThat(request.getActive(), IsEqual.equalTo(false));
    }

    @Test
    public void testGetPicture() throws Exception {
        assertThat(request.getPicture(), IsEqual.equalTo("pic"));
    }

    @Test
    public void testSetPicture() throws Exception {
        request.setPicture("new_pic");
        assertThat(request.getPicture(), IsEqual.equalTo("new_pic"));
    }

    @Test
    public void testGetText() throws Exception {
        assertThat(request.getText(), IsEqual.equalTo("content"));
    }

    @Test
    public void testSetText() throws Exception {
        request.setText("new_content");
        assertThat(request.getText(), IsEqual.equalTo("new_content"));
    }

    @Test
    public void testGetCreationDate() throws Exception {
        assertThat(request.getCreationDate(), IsEqual.equalTo(nowDate));
    }

    @Test
    public void testSetCreationDate() throws Exception {
        Date date = new Date();
        request.setCreationDate(date);
        assertThat(request.getCreationDate(), IsEqual.equalTo(date));
    }

    @Test
    public void testGetMagnets() throws Exception {
        assertThat(request.getMagnets(), IsEqual.equalTo(new ArrayList<>()));
    }

    @Test
    public void testSetMagnets() throws Exception {
        ArrayList<Magnet> magnets = new ArrayList<>();
        magnets.add(new Magnet());
        request.setMagnets(magnets);

        assertThat(request.getMagnets(), IsEqual.equalTo(magnets));
    }

    @Test
    public void testAddMagnet() throws Exception {
        ArrayList<Magnet> magnets = new ArrayList<>();
        Magnet m1 = new Magnet();
        magnets.add(m1);

        request.addMagnet(m1);

        assertThat(request.getMagnets(), IsEqual.equalTo(magnets));
    }

    @Test
    public void testRemoveMagnet() throws Exception {
        ArrayList<Magnet> magnets = new ArrayList<>();
        Magnet m1 = new Magnet();
        magnets.add(m1);

        request.setMagnets(magnets);
        assertThat(request.getMagnets(), IsEqual.equalTo(magnets));
        request.removeMagnet(m1);
        assertThat(request.getMagnets(), IsEqual.equalTo(new ArrayList<Magnet>()));
    }

    @Test
    public void testGetComments() throws Exception {
        assertThat(request.getComments(), IsEqual.equalTo(new ArrayList<>()));
    }

    @Test
    public void testSetComments() throws Exception {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        request.setComments(comments);

        assertThat(request.getComments(), IsEqual.equalTo(comments));
    }

    @Test
    public void testAddComment() throws Exception {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment c1 = new Comment();
        comments.add(c1);

        request.addComment(c1);

        assertThat(request.getComments(), IsEqual.equalTo(comments));
    }

    @Test
    public void testRemoveComment() throws Exception {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment c1 = new Comment();
        comments.add(c1);

        request.setComments(comments);
        assertThat(request.getComments(), IsEqual.equalTo(comments));
        request.removeComment(c1);
        assertThat(request.getComments(), IsEqual.equalTo(new ArrayList<Comment>()));
    }

    @Test
    public void testEquals() throws Exception {
        Date date = new Date(155409322);

        // same attributes
        Request req = new Request(2L, true, "pic", "content", nowDate, new ArrayList<>(), new ArrayList<>());
        // different creationDate
        Request req2 = new Request(3L, true, "pic", "content", date, new ArrayList<>(), new ArrayList<>());
        // different text
        Request req3 = new Request(4L, true, "pic", "other", date, new ArrayList<>(), new ArrayList<>());
        // text null
        Request req4 = new Request(5L, true, "pic", null, date, new ArrayList<>(), new ArrayList<>());
        // different picture
        Request req5 = new Request(4L, true, "other", "content", date, new ArrayList<>(), new ArrayList<>());
        // picture null
        Request req6 = new Request(5L, true, null, "content", date, new ArrayList<>(), new ArrayList<>());
        // different isActive
        Request req7 = new Request(6L, false, "pic", "content", date, new ArrayList<>(), new ArrayList<>());

        assertTrue(request.equals(req));
        assertTrue(request.equals(request));
        assertFalse(request.equals(null));
        assertFalse(request.equals(""));
        assertFalse(request.equals(req2));
        assertFalse(request.equals(req3));
        assertFalse(request.equals(req4));
        assertFalse(request.equals(req5));
        assertFalse(request.equals(req6));
        assertFalse(request.equals(req7));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(request.toString(), IsEqual.equalTo("Request{" +
                "isActive=" + true +
                ", picture='" + "pic" + '\'' +
                ", text='" + "content" + '\'' +
                ", creationDate=" + nowDate +
                ", magnets=" + new ArrayList<>() +
                ", comments=" + new ArrayList<>() +
                '}'));
    }

    @Test
    public void testHashCode() throws Exception {
        Request req = new Request(2L, true, "pic", "content", nowDate, new ArrayList<>(), new ArrayList<>());

        assertThat(request.hashCode(), IsEqual.equalTo(req.hashCode()));
    }
}