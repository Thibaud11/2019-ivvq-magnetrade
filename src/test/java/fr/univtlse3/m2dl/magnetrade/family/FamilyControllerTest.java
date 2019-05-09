package fr.univtlse3.m2dl.magnetrade.family;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class FamilyControllerTest {

    private FamilyController magnetController;

    @MockBean
    private FamilyService magnetService;

    @MockBean
    private Family magnet;

    @Before
    public void setUp(){
        magnetController = new FamilyController();
        magnet = new Family("USA");
        magnetController.setFamilyService(magnetService);
    }

    @Test
    public void saveFromCrudRepositoryIsInvokedWhenFamilySavedTest() {
        //when: la méthode savemagnet est invoquée
        magnetController.createFamily(magnet);
        // then: la méthode save du magnetRepository associé est invoquée
        verify(magnetController.getFamilyService()).saveFamily(magnet);
    }

    @Test
    public void deleteFromCrudRepositoryIsInvokedWhenFamilySavedTest() {
        //when: la méthode savemagnet est invoquée
        magnetController.deleteFamily(0L);
        // then: la méthode save du magnetRepository associé est invoquée
        verify(magnetController.getFamilyService()).deleteFamily(0L);
    }

    @Test
    public void findByIdFromCrudRepositoryIsInvokedWhenmagnetIsFoundByIdTest() {
        //given: un magnetController
        //when: la méthode findmagnetById est invoquée
        magnetController.findFamilyById(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(magnetController.getFamilyService()).findFamilyById(0L);
    }

    @Test
    public void findAllFromCrudRepositoryIsInvokedWhenFindAllFamilyTest() {
        //given: un magnetController
        //when: la méthode findAllmagnet est invoquée
        magnetController.findAllFamily();
        //then: la méthode findAll du Repository associé est invoquée
        verify(magnetController.getFamilyService()).findAllFamilys();
    }

    @Test
    public void setFamilyRepositoryTest() {
        magnetController.setFamilyService(null);
        assertThat(magnetController.getFamilyService(), nullValue());
    }
}