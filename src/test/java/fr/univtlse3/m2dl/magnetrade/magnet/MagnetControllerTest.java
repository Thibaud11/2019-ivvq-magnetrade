package fr.univtlse3.m2dl.magnetrade.magnet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class MagnetControllerTest {

    private MagnetController magnetController;

    @MockBean
    private MagnetService magnetService;

    @MockBean
    private Magnet magnet;

    @Before
    public void setUp(){
        magnetController = new MagnetController();
        magnet = new Magnet(MagnetTest.MAGNET_NAME, MagnetTest.MAGNET_PICTURE_URL, MagnetTest.MAGNET_DESCRIPTION, MagnetTest.MAGNET_FAMILY);
        magnetController.setMagnetService(magnetService);
    }

    @Test
    public void saveFromCrudRepositoryIsInvokedWhenMagnetSavedTest() {
        //when: la méthode savemagnet est invoquée
        magnetController.createMagnet(magnet);
        // then: la méthode save du magnetRepository associé est invoquée
        verify(magnetController.getMagnetService()).saveMagnet(magnet);
    }

    @Test
    public void deleteFromCrudRepositoryIsInvokedWhenMagnetSavedTest() {
        //when: la méthode savemagnet est invoquée
        magnetController.deleteMagnet(0L);
        // then: la méthode save du magnetRepository associé est invoquée
        verify(magnetController.getMagnetService()).deleteMagnet(0L);
    }

    @Test
    public void findByIdFromCrudRepositoryIsInvokedWhenmagnetIsFoundByIdTest() {
        //given: un magnetController
        //when: la méthode findmagnetById est invoquée
        magnetController.findMagnetById(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(magnetController.getMagnetService()).findMagnetById(0L);
    }

    @Test
    public void findAllFromCrudRepositoryIsInvokedWhenFindAllMagnetTest() {
        //given: un magnetController
        //when: la méthode findAllmagnet est invoquée
        magnetController.findAllMagnet();
        //then: la méthode findAll du Repository associé est invoquée
        verify(magnetController.getMagnetService()).findAllMagnets();
    }

    @Test
    public void setMagnetRepositoryTest() {
        magnetController.setMagnetService(null);
        assertThat(magnetController.getMagnetService(), nullValue());
    }
}