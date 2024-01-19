package lorenza._5Month2WeekProject.controllers;

import lorenza._5Month2WeekProject.entities.Users;
import lorenza._5Month2WeekProject.services.UserService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<Users> getUsers(@RequestParam int page, @RequestParam int size,@RequestParam String orderBy) {
        return userService.getUsers(page, size, orderBy);
    }

    @GetMapping("/{userUUID}")
    public Users getUserByUUID(@PathVariable UUID userUUID) {
        return userService.findById(userUUID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Users createUser(@RequestBody Users newUserPayload) {
        return userService.save(newUserPayload);
    }

    @PutMapping("/{userUUID}")
    public Users getUserByUUIDAndUpdate(@PathVariable UUID userUUID, @RequestBody Users modifiedUserPayload) {
        return userService.findByIdAndUpdate(userUUID, modifiedUserPayload);
    }

    @DeleteMapping("/{userUUID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByUUIDAndDelete(@PathVariable UUID userUUID) {
        userService.findByIdAndDelete(userUUID);
    }

}
