package fr.univtlse3.m2dl.magnetrade.user;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class UserServiceTest {
    UserService userService;
    @MockBean
    UserRepository userRepository;

    @Before
    public void setup() {
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
    }

    /*@Test
    public void testsetUserRepository() {
        userService.setUserRepository(null);
        assert(userService.getUserRepository() == null);
    }
    @Test
    public void testGetUserRepository() {
        assert(userRepository.equals(userService.getUserRepository()));
    }*/
    @Test
    public void testSaveUser() {

    }

}