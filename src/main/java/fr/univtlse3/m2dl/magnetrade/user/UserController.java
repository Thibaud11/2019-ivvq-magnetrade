package fr.univtlse3.m2dl.magnetrade.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/edit")
    @ResponseStatus(HttpStatus.CREATED)
    public User editUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

}
