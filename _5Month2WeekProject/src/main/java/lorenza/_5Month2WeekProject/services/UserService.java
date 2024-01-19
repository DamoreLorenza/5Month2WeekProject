package lorenza._5Month2WeekProject.services;

import lorenza._5Month2WeekProject.entities.Users;
import lorenza._5Month2WeekProject.exception.NotFoundException;
import lorenza._5Month2WeekProject.repositories.UsersDAO;
import org.apache.catalina.User;
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

    public Users save(Users body) {
        return usersDAO.save(body);
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