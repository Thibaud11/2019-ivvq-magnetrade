package fr.univtlse3.m2dl.magnetrade.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private User user;

    @Before
    public void setup(){
        userController = new UserController();
        userController.setUserService(userService);
        user =  new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dSDF", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
    }

    @Test
    public void testGetUserService(){
        assert(userService.equals(userController.getUserService()));
    }

    @Test
    public void testSetUserService() {
        userController.setUserService(null);
        assertThat(userService.getUserRepository(), nullValue());
    }

    @Test
    public void testCreateUser(){
        //when: la méthode createUser est invoquée
        userController.createUser(user);
        // then: la méthode saveUser du userService associé est invoquée
        verify(userController.getUserService()).createOrUpdateUser(user);
    }

    @Test
    public void testDeleteUser(){
        //when: la méthode createUser est invoquée
        userController.deleteUser(0L);
        // then: la méthode saveUser du userService associé est invoquée
        verify(userController.getUserService()).deleteUser(0L);
    }

    @Test
    public void testFindUserById() throws Exception {
        try {
            //when: la méthode createUser est invoquée
            userController.findUserById(0L);
            // then: la méthode saveUser du userService associé est invoquée
            verify(userController.getUserService()).findUserById(0L);
        } catch (ResponseStatusException ignored) {
            // Empty
        }
    }

}
