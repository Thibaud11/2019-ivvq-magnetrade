package fr.univtlse3.m2dl.magnetrade.usermagnet;

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
public class UserMagnetServiceTest {

    private UserMagnetService userMagnetService;

    @MockBean
    private UserMagnetRepository userMagnetRepository;

    @MockBean
    private UserMagnet userMagnet;

    @Before
    public void setUp(){
        userMagnetService = new UserMagnetService();
        userMagnet = new UserMagnet();
        userMagnetService.setUserMagnetRepository(userMagnetRepository);
    }

    @Test
    public void typeRepositoryTest() {
         //le Repository associé à un userMagnetService est de type CrudRepository
        assertThat(userMagnetService.getUserMagnetRepository(), instanceOf(CrudRepository.class));
    }

    @Test
    public void saveFromCrudRepositoryIsInvokedWhenUserMagnetSavedTest() {
        //when: la méthode saveuserMagnet est invoquée
        userMagnetService.saveUserMagnet(userMagnet);
        // then: la méthode save du userMagnetRepository associé est invoquée
        verify(userMagnetService.getUserMagnetRepository()).save(userMagnet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenNullUserMagnetSavedTest() {
        //when: la méthode saveuserMagnet est invoquée avec null en param
        userMagnetService.saveUserMagnet(null);
    }

    @Test
    public void deleteFromCrudRepositoryIsInvokedWhenUserMagnetSavedTest() {
        //when: la méthode saveuserMagnet est invoquée
        userMagnetService.deleteUserMagnet(0L);
        // then: la méthode save du userMagnetRepository associé est invoquée
        verify(userMagnetService.getUserMagnetRepository()).deleteById(0L);
    }

    @Test
    public void findByIdFromCrudRepositoryIsInvokedWhenuserMagnetIsFoundByIdTest() {
        //given: un userMagnetService
        //when: la méthode finduserMagnetById est invoquée
        userMagnetService.findUserMagnetById(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(userMagnetService.getUserMagnetRepository()).findById(0L);
    }

    @Test
    public void setUserMagnetRepositoryTest() {
        userMagnetService.setUserMagnetRepository(null);
        assertThat(userMagnetService.getUserMagnetRepository(), nullValue());
    }
}