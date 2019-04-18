package fr.univtlse3.m2dl.magnetrade.magnet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MagnetTest {

    private static Validator validator;

    private static Magnet magnet;
    private static Magnet newMagnet;

    final public static String MAGNET_NAME = "MADAGASCAR";
    final public static String MAGNET_PICTURE_URL = "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwigpcHz55XhAhUEJBoKHbeqAnIQjRx6BAgBEAU&url=https%3A%2F%2Fwww.world-wide-gifts.com%2Fsouvenirs%2F00008084-metal-fridge-magnet-madagascar-map-and-flag-of-madagascar-map-shaped%2F&psig=AOvVaw35ZArOyUpht-Jc41asckXs&ust=1553346261528026";
    final public static String MAGNET_DESCRIPTION = "Un magnet pour madagscar";

    final public static String NEW_MAGNET_NAME = "FRANCE";
    final public static String NEW_MAGNET_PICTURE_URL = "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwifq4DA6ZXhAhX2BGMBHceKDhcQjRx6BAgBEAU&url=https%3A%2F%2Fwww.amazon.com%2FFlagline-France-Magnet%2Fdp%2FB000EOFOJO&psig=AOvVaw2wL56VoiooietOzc9kaVga&ust=1553346692268254";
    final public static String NEW_MAGNET_DESCRIPTION = "Champion du monde";

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void resetUp() {
        magnet = new Magnet(MAGNET_NAME, MAGNET_PICTURE_URL, MAGNET_DESCRIPTION);
        newMagnet = new Magnet(MAGNET_NAME, MAGNET_PICTURE_URL, MAGNET_DESCRIPTION);
    }

    @Test
    public void testEqualsWithNullArgIsFalse() {
        boolean actual = magnet.equals(null);
        assertThat(actual, is(false));
    }

    @Test
    public void testGetName() {
        String actual = magnet.getName();
        assertThat(actual, is(MAGNET_NAME));
    }

    @Test
    public void testSetName() {
        magnet.setName(NEW_MAGNET_NAME);
        assertThat(NEW_MAGNET_NAME, is(magnet.getName()));
    }

    @Test
    public void testGetPictureURL() {
        String actual = magnet.getPictureURL();
        assertThat(actual, is(MAGNET_PICTURE_URL));
    }

    @Test
    public void testSetPictureURL() {
        magnet.setPictureURL(NEW_MAGNET_PICTURE_URL);
        assertThat(NEW_MAGNET_PICTURE_URL, is(magnet.getPictureURL()));
    }

    @Test
    public void testGetDescription() {
        String actual = magnet.getDescription();
        assertThat(actual, is(MAGNET_DESCRIPTION));
    }

    @Test
    public void testSetDescription() {
        magnet.setDescription(NEW_MAGNET_DESCRIPTION);
        assertThat(NEW_MAGNET_DESCRIPTION, is(magnet.getDescription()));
    }

    @Test
    public void testEquals() {
        assertThat(magnet.equals(newMagnet), is(true));
    }

    @Test
    public void testMagnetPicture() {
        assertThat(validator.validate(magnet).isEmpty(), is(true));
    }

    @Test
    public void testMagnetNameNotNull() {
        magnet.setName(null);
        assertThat(validator.validate(magnet).isEmpty(), is(false));
    }

    @Test
    public void testMagnetPictureURLNotNull() {
        magnet.setPictureURL(null);
        assertThat(validator.validate(magnet).isEmpty(), is(false));
    }


}