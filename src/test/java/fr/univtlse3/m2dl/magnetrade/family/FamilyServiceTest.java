package fr.univtlse3.m2dl.magnetrade.family;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class FamilyServiceTest {

    private FamilyService familyService;

    @MockBean
    private FamilyRepository familyRepository;

    @MockBean
    private Family family;

    @Before
    public void setUp(){
        familyService = new FamilyService();
        family = new Family("USA");
        familyService.setFamilyRepository(familyRepository);
    }

    @Test
    public void typeRepositoryTest() {
         //le Repository associé à un familyService est de type CrudRepository
        assertThat(familyService.getFamilyRepository(), instanceOf(CrudRepository.class));
    }

    @Test
    public void saveFromCrudRepositoryIsInvokedWhenFamilySavedTest() {
        //when: la méthode savefamily est invoquée
        familyService.saveFamily(family);
        // then: la méthode save du familyRepository associé est invoquée
        verify(familyService.getFamilyRepository()).save(family);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenNullFamilySavedTest() {
        //when: la méthode savefamily est invoquée avec null en param
        familyService.saveFamily(null);
    }

    @Test
    public void deleteFromCrudRepositoryIsInvokedWhenFamilySavedTest() {
        //when: la méthode savefamily est invoquée
        familyService.deleteFamily(0L);
        // then: la méthode save du familyRepository associé est invoquée
        verify(familyService.getFamilyRepository()).deleteById(0L);
    }

    @Test
    public void findByIdFromCrudRepositoryIsInvokedWhenfamilyIsFoundByIdTest() {
        //given: un familyService
        //when: la méthode findfamilyById est invoquée
        familyService.findFamilyById(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(familyService.getFamilyRepository()).findById(0L);
    }

    @Test
    public void findAllFromCrudRepositoryIsInvokedWhenFindAllFamilyTest() {
        //given: un familyService
        //when: la méthode findAllfamily est invoquée
        familyService.findAllFamilys();
        //then: la méthode findAll du Repository associé est invoquée
        verify(familyService.getFamilyRepository()).findAll();
    }

    @Test
    public void setFamilyRepositoryTest() {
        familyService.setFamilyRepository(null);
        assertThat(familyService.getFamilyRepository(), nullValue());
    }
}