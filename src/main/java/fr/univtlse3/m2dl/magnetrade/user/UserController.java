package fr.univtlse3.m2dl.magnetrade.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Method to create or update a user.
     * @param user user to create/update
     * @return user created/updated
     */
    @PostMapping({"/create", "/edit"})
    public User createUser(@RequestBody User user) {
        return userService.createOrUpdateUser(user);
    }

    /**
     * Method to delete a user.
     * @param id id of the user to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    /**
     * Method to find all users.
     * @return all users
     */
    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * Method to find a user.
     * @param id id of the user to find
     * @return
     */
    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UserService getUserService(){
        return userService;
    }

    public void setUserService(UserService userService){
        this.userService = userService;
    }

}
