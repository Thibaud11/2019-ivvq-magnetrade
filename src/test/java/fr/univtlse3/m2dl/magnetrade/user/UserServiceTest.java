package fr.univtlse3.m2dl.magnetrade.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private User user;

    @Before
    public void setup() {
        userService = new UserService();
        userService.setUserRepository(userRepository);
        user =  new User("Louis", "JACQUES", "loulou@lou.lou", new Date(1995, 10, 29), "dSDF", "lepetitbonhommenemousse", "+33628813045", "/resources/bigsmile.jpg");
    }

    @Test
    public void saveFromCrudRepositoryIsInvokedWhenuUserSavedTest() {
        //when: la méthode savemagnet est invoquée
        userService.saveUser(user);
        // then: la méthode save du magnetRepository associé est invoquée
        verify(userService.getUserRepository()).save(user);
    }
    @Test
    public void findByIdFromCrudRepositoryIsInvokedWhenUserIsFoundByIdTest() {
        //given: un userService
        //when: la méthode findUserById est invoquée
        userService.findUserById(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(userService.getUserRepository()).findById(0L);
    }

    @Test
    public void testGetUserRepository() {
        assert(userRepository.equals(userService.getUserRepository()));
    }
    @Test
    public void setUserRepositoryTest() {
        userService.setUserRepository(null);
        assertThat(userService.getUserRepository(), nullValue());
    }

    @Test
    public void deleteFromCrudRepositoryWhenDeletingUserTest(){
        //given: un userService
        //when: la méthode findUserById est invoquée
        userService.deleteUser(0L);
        //then: la méthode findById du Repository associé est invoquée
        verify(userService.getUserRepository()).deleteById(0L);
    }


}