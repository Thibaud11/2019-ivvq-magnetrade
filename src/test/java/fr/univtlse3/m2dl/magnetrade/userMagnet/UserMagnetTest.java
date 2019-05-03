package fr.univtlse3.m2dl.magnetrade.userMagnet;

import fr.univtlse3.m2dl.magnetrade.family.Family;
import fr.univtlse3.m2dl.magnetrade.family.FamilyTest;
import fr.univtlse3.m2dl.magnetrade.magnet.Magnet;
import fr.univtlse3.m2dl.magnetrade.magnet.MagnetTest;
import fr.univtlse3.m2dl.magnetrade.user.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Date;

import static fr.univtlse3.m2dl.magnetrade.magnet.MagnetTest.MAGNET_FAMILY;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserMagnetTest {

    private static Validator validator;

    private static UserMagnet userMagnet;
    private static UserMagnet newUserMagnet;
    private static Family family = new Family("USA");

    final public static Magnet MAGNET = new Magnet(MagnetTest.MAGNET_NAME, MagnetTest.MAGNET_PICTURE_URL, MagnetTest.MAGNET_DESCRIPTION, family);
    final public static Magnet NEW_MAGNET = new Magnet(MagnetTest.NEW_MAGNET_NAME, MagnetTest.NEW_MAGNET_PICTURE_URL, MagnetTest.NEW_MAGNET_DESCRIPTION, family);
    final public static User USER =  new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dank zoulou veineux", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
    final public static User NEW_USER = new User("a", "a", "la@a.a", new Date(2, 2, 2), "dank 2 veineux", "2", "+2", "/2/s.jpg");


    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void resetUp() {
        userMagnet = new UserMagnet(0, MAGNET, USER);
        newUserMagnet = new UserMagnet(0, MAGNET, USER);
    }

    @Test
    public void testGetNumber() {
        int actual = userMagnet.getNumber();
        assertThat(actual, is(0));
    }

    @Test
    public void testSetNumber() {
        userMagnet.setNumber(1);
        assertThat(1, is(userMagnet.getNumber()));
    }

    @Test
    public void testGetMagnet() {
        Magnet actual = userMagnet.getMagnet();
        assertThat(actual, is(MAGNET));
    }

    @Test
    public void testSetMagnet() {
        userMagnet.setMagnet(NEW_MAGNET);
        assertThat(NEW_MAGNET, is(userMagnet.getMagnet()));
    }

    @Test
    public void testGetUser() {
        User actual = userMagnet.getUser();
        assertThat(actual, is(USER));
    }

    @Test
    public void testSetUser() {
        userMagnet.setUser(NEW_USER);
        assertThat(NEW_USER, is(userMagnet.getUser()));
    }

    @Test
    public void testEquals() {
        assertThat(userMagnet.equals(newUserMagnet), is(true));
    }

    @Test
    public void testUserMagnetMagnetNotNull() {
        userMagnet.setMagnet(null);
        assertThat(validator.validate(userMagnet).isEmpty(), is(false));
    }

    @Test
    public void testUserMagnetUserNotNull() {
        userMagnet.setUser(null);
        assertThat(validator.validate(userMagnet).isEmpty(), is(false));
    }
}