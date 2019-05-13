package fr.univtlse3.m2dl.magnetrade.family;

import fr.univtlse3.m2dl.magnetrade.magnet.Magnet;
import fr.univtlse3.m2dl.magnetrade.magnet.MagnetTest;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FamilyTest {

    private static Validator validator;

    final public static Magnet NEW_FAMILY_MAGNET = new Magnet(MagnetTest.MAGNET_NAME, MagnetTest.MAGNET_PICTURE_URL, MagnetTest.MAGNET_DESCRIPTION, MagnetTest.MAGNET_FAMILY);

    private static Family family = new Family("USA");
    private static Family newFamily = new Family("USA");

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void resetUp() {
        family = new Family("USA");
        newFamily = new Family("USA");
    }

    @Test
    public void testGetId() {
        Long actual = family.getId();
        assertThat(actual, is(IsNull.nullValue()));
    }

    @Test
    public void testSetId() {
        family.setId(10L);
        assertThat(10L, is(family.getId()));
    }

    @Test
    public void testGetName() {
        String actual = family.getName();
        assertThat(actual, is("USA"));
    }

    @Test
    public void testSetName() {
        family.setName(MagnetTest.NEW_MAGNET_NAME);
        assertThat(MagnetTest.NEW_MAGNET_NAME, is(family.getName()));
    }

    @Test
    public void testFamilyNameNotNull() {
        family.setName(null);
        assertThat(validator.validate(family).isEmpty(), is(false));
    }

    @Test
    public void testGetMagnet() {
        List<Magnet> actual = family.getMagnets();
        assertThat(actual.size(), is(0));
    }

    @Test
    public void testSetMagnets() {
        List<Magnet> lm = new ArrayList<>();
        lm.add(NEW_FAMILY_MAGNET);
        family.setMagnets(lm);
        assertThat(lm, is(family.getMagnets()));
    }

    @Test
    public void testEquals() {
        assertThat(family.equals(newFamily), is(true));
    }
}