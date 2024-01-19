package lorenza._5Month2WeekProject.services;

import lorenza._5Month2WeekProject.entities.Device;
import lorenza._5Month2WeekProject.entities.Users;
import lorenza._5Month2WeekProject.exception.NotFoundException;
import lorenza._5Month2WeekProject.repositories.DeviceDAO;
import lorenza._5Month2WeekProject.repositories.UsersDAO;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.UUID;

@Service
public class DeviceService {

    @Autowired
    private DeviceDAO deviceDAO;

    public Page<Device> getDevice(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return deviceDAO.findAll(pageable);
    }

    public Device save(Device body) {
        return deviceDAO.save(body);
    }

    public Device findById(UUID uuid) {
        return deviceDAO.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public void findByIdAndDelete(UUID uuid) {
        Device found = this.findById(uuid);
        deviceDAO.delete(found);
    }

    public Device findByIdAndUpdate(UUID uuid, Device body) {
        Device found = this.findById(uuid);
        found.setType(body.getType());
        found.setState(body.getState());
        return deviceDAO.save(found);
    }


}