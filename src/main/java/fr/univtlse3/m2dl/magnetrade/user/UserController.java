package fr.univtlse3.m2dl.magnetrade.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"/create", "/edit"})
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);

    }

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable("id") Long id) throws Exception{
        if(userService.findUserById(id) != null){
            return userService.findUserById(id);
        }else {
           return null;
        }

    }

    public UserService getUserService(){
        return userService;
    }

    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
