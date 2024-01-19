package lorenza._5Month2WeekProject.controllers;

import lorenza._5Month2WeekProject.entities.Users;
import lorenza._5Month2WeekProject.services.UserService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<Users> getUsers(@RequestParam int page, @RequestParam int size,@RequestParam String orderBy) {
        return userService.getUsers(page, size, orderBy);
    }

}
