package fr.univtlse3.m2dl.magnetrade.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){

        return userService.saveUser(user);
    }
}
