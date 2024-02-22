package comjwtdb.Controller;

import comjwtdb.Entity.User;
import comjwtdb.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUser()
    {
        System.out.println("getting users...");
        return userService.getUsers();
    }
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/test")
    public String test() {
        this.logger.warn("This is working message");
        return "Testing message";
    }



}