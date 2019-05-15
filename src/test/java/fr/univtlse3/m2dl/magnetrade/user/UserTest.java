package fr.univtlse3.m2dl.magnetrade.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    private User user;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;
    private User user7;
    private User user8;
    private User user9;
    private User user10;

    @Before
    public void setup() {
        user =  new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dank zoulou veineux", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
        user2 = new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dank zoulou veineux", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
        user3 = new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dank zoulou veineux", "lepetitbonhommenemousse", "+33628813045", "/resources/s.jpg");
        user4 = new User("a", "a", "la@a.a", new Date(2, 2, 2), "dank 2 veineux", "2", "+2", "/2/s.jpg");
        user5 = new User("Louis", "a", "loulou@lou.lou", new Date(1995, 10, 29), "dank zoulou veineux", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
        user6 = new User("Louis", "JACQUES", "a", new Date(1995, 10, 29), "dank zoulou veineux", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
        user7 = new User("Louis", "JACQUES", "loulou@lou.lou", new Date(7, 10, 29), "dank zoulou veineux", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
        user8 = new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dank zoulou s", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
        user9 = new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dank zoulou veineux", "s", "+33628813045", "/resources/bigsmile.jpg");
        user10 = new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dank zoulou veineux", "lepetitbonhommenemousse", "+d", "/resources/bigsmile.jpg");
    }

    @Test
    public void testGetFirstName() {
        assert (user.getFirstName().equals("Louis"));
    }

    @Test
    public void testGetLastName() {
        assert (user.getLastName().equals("JACQUES"));
    }

    @Test
    public void testGetEmail() {
        assert (user.getEmailName().equals("loulou@lou.lou"));
    }

    @Test
    public void testGetBirthDate() {
        assert (user.getBirthDate().equals(new Date(1995, 10, 29)));
    }

    @Test
    public void testGetNickName() {
        assert (user.getNickName().equals("dank zoulou veineux"));
    }

    @Test
    public void testGetPasswordName() {
        assert (user.getPassword().equals("lepetitbonhommenemousse"));
    }

    @Test
    public void testGetPhoneNumber() {
        assert (user.getPhoneNumber().equals("+33628813045"));
    }

    @Test
    public void testGetPicture() {
        assert (user.getPicture().equals("/resources/bigsmile.jpg"));
    }

    @Test
    public void testSetId() {
        user.setId(987L);
        assert (user.getId() == 987L);
    }

    @Test
    public void testGetId() {
        Long id = user2.id;
        assert (user2.getId() == id);
    }

    @Test
    public void testSetFirstName() {
        user.setFirstName("oh");
        assert (user.getFirstName().equals("oh"));
    }

    @Test
    public void testSetLastName() {
        user.setLastName("ah");
        assert (user.getLastName().equals("ah"));
    }

    @Test
    public void testSetEmail() {
        user.setEmailName("COUCOU@h.com");
        assert (user.getEmailName().equals("COUCOU@h.com"));
    }

    @Test
    public void testSetNickName() {
        user.setNickName("nick");
        assert (user.getNickName().equals("nick"));
    }

    @Test
    public void testSetPasswordName() {
        user.setPassword("pwd123");
        assert (user.getPassword().equals("pwd123"));
    }

    @Test
    public void testSetPhoneNumber() {
        user.setPhoneNumber("0505050505");
        assert (user.getPhoneNumber().equals("0505050505"));
    }

    @Test
    public void testSetPicture() {
        user.setPicture("/void.jpg");
        assert (user.getPicture().equals("/void.jpg"));
    }

    @Test
    public void testEquals() {
        assert (user.equals(user2));
    }

    @Test
    public void testNotEquals() {
        assertNotEquals(user, null);
        assertNotEquals(user, user3);
        assertNotEquals(user, user4);
        assertNotEquals(user, user5);
        assertNotEquals(user, user6);
        assertNotEquals(user, user7);
        assertNotEquals(user, user8);
        assertNotEquals(user, user9);
        assertNotEquals(user, user10);
    }

    @Test
    public void testHashCode() {
        assertEquals(user.hashCode(), user2.hashCode());
    }

}
