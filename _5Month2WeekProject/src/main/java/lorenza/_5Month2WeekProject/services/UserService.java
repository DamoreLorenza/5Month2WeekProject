package lorenza._5Month2WeekProject.services;

import lorenza._5Month2WeekProject.entities.Users;
import lorenza._5Month2WeekProject.exception.NotFoundException;
import lorenza._5Month2WeekProject.payloads.NewUserDTO;
import lorenza._5Month2WeekProject.repositories.UsersDAO;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UsersDAO usersDAO;

    public Page<Users> getUsers(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return usersDAO.findAll(pageable);
    }

    public Users save(NewUserDTO body) {
        Users newUser = new Users();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        return usersDAO.save(newUser);
    }

    public Users findById(UUID uuid) {
        return usersDAO.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public void findByIdAndDelete(UUID uuid) {
        Users found = this.findById(uuid);
        usersDAO.delete(found);
    }

    public Users findByIdAndUpdate(UUID uuid, Users body) {
        Users found = this.findById(uuid);
        found.setUsername(body.getUsername());
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        return usersDAO.save(found);
    }


}