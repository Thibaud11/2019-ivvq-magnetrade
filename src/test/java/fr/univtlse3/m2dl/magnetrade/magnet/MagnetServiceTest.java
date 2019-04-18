package fr.univtlse3.m2dl.magnetrade.magnet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class MagnetServiceTest {

    private MagnetService magnetService;

    @MockBean
    private MagnetRepository magnetRepository;

    @MockBean
    private Magnet magnet;

    @Before
    public void setUp(){
        magnetService = new MagnetService();
        magnet = new Magnet(MagnetTest.MAGNET_NAME, MagnetTest.MAGNET_PICTURE_URL, MagnetTest.MAGNET_DESCRIPTION);
        magnetService.setMagnetRepository(magnetRepository);
    }

    @Test
    public void typeRepositoryTest() {
         //le Repository associé à un magnetService est de type CrudRepository
        assertThat(magnetService.getMagnetRepository(), instanceOf(CrudRepository.class));
    }

    @Test
    public void saveFromCrudRepositoryIsInvokedWhenMagnetSavedTest() {
        //when: la méthode savemagnet est invoquée
        magnetService.saveMagnet(magnet);
        // then: la méthode save du magnetRepository associé est invoquée
        verify(magnetService.getMagnetRepository()).save(magnet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenNullMagnetSavedTest() {
        //when: la méthode savemagnet est invoquée avec null en param
        magnetService.saveMagnet(null);
    }

    @Test
    public void deleteFromCrudRepositoryIsInvokedWhenMagnetSavedTest() {
        //when: la méthode savemagnet est invoquée
        magnetService.deleteMagnet(0L);
        // then: la méthode save du magnetRepository associé est invoquée
        verify(magnetService.getMagnetRepository()).deleteById(0L);
    }

    @Test
    public void findByIdFromCrudRepositoryIsInvokedWhenmagnetIsFoundByIdTest() {
        //given: un magnetService
        //when: la méthode findmagnetById est invoquée
        magnetService.findMagnetById(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(magnetService.getMagnetRepository()).findById(0L);
    }

    /*@Test
    public void FindAllFromCrudRepositoryIsInvokedWhenFindAllMagnetTest() {
        //given: un magnetService
        //when: la méthode findAllmagnet est invoquée
        magnetService.findAllMagnets();
        //then: la méthode findAll du Repository associé est invoquée
        verify(magnetService.getMagnetRepository()).findAll();
    }*/

    @Test
    public void setMagnetRepositoryTest() {
        magnetService.setMagnetRepository(null);
        assertThat(magnetService.getMagnetRepository(), nullValue());
    }
}