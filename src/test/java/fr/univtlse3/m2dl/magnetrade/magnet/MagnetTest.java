package fr.univtlse3.m2dl.magnetrade.magnet;

import org.hamcrest.core.IsNull;
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
    final public static String MAGNET_PICTURE_URL = "fakePictureURL.com";
    final public static String MAGNET_DESCRIPTION = "Un magnet pour madagscar";

    final public static String NEW_MAGNET_NAME = "FRANCE";
    final public static String NEW_MAGNET_PICTURE_URL = "fakePictureURL.com";
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
    public void testGetId() {
        Long actual = magnet.getId();
        assertThat(actual, is(IsNull.nullValue()));
    }

    @Test
    public void testSetId() {
        magnet.setId(10L);
        assertThat(10L, is(magnet.getId()));
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