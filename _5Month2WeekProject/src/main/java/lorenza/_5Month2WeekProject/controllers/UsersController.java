package lorenza._5Month2WeekProject.controllers;

import lorenza._5Month2WeekProject.entities.Users;
import lorenza._5Month2WeekProject.exception.BadRequestException;
import lorenza._5Month2WeekProject.payloads.NewUserDTO;
import lorenza._5Month2WeekProject.payloads.NewUserResponseDTO;
import lorenza._5Month2WeekProject.services.UserService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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


    @PutMapping("/{userUUID}")
    public Users getUserByUUIDAndUpdate(@PathVariable UUID userUUID, @RequestBody Users modifiedUserPayload) {
        return userService.findByIdAndUpdate(userUUID, modifiedUserPayload);
    }

    @DeleteMapping("/{userUUID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByUUIDAndDelete(@PathVariable UUID userUUID) {
        userService.findByIdAndDelete(userUUID);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload");
        } else {
            Users newUser = userService.save(newUserPayload);

        }
        return new NewUserResponseDTO(newUserPayload.getUUID());
    }
}
