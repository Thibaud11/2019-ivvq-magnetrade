package fr.univtlse3.m2dl.magnetrade.usermagnet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UserMagnetControllerTest {

    private UserMagnetController userMagnetController;

    @MockBean
    private UserMagnetService userMagnetService;

    @MockBean
    private UserMagnet userMagnet;

    @Before
    public void setUp(){
        userMagnetController = new UserMagnetController();
        userMagnet = new UserMagnet(0, UserMagnetTest.MAGNET, UserMagnetTest.USER);
        userMagnetController.setUserMagnetService(userMagnetService);
    }

    @Test
    public void saveFromCrudRepositoryIsInvokedWhenUserMagnetSavedTest() {
        //when: la méthode saveuserUserMagnet est invoquée
        userMagnetController.createUserMagnet(userMagnet);
        // then: la méthode save du userMagnetRepository associé est invoquée
        verify(userMagnetController.getUserMagnetService()).saveUserMagnet(userMagnet);
    }

    @Test
    public void deleteFromCrudRepositoryIsInvokedWhenMagnetSavedTest() {
        //when: la méthode saveuserMagnet est invoquée
        userMagnetController.deleteUserMagnet(0L);
        // then: la méthode save du userMagnetRepository associé est invoquée
        verify(userMagnetController.getUserMagnetService()).deleteUserMagnet(0L);
    }

    @Test
    public void findByIdFromCrudRepositoryIsInvokedWhenuserMagnetIsFoundByIdTest() {
        //given: un userMagnetController
        //when: la méthode finduserMagnetById est invoquée
        userMagnetController.findUserMagnetById(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(userMagnetController.getUserMagnetService()).findUserMagnetById(0L);
    }

    @Test
    public void setUserMagnetRepositoryTest() {
        userMagnetController.setUserMagnetService(null);
        assertThat(userMagnetController.getUserMagnetService(), nullValue());
    }
}