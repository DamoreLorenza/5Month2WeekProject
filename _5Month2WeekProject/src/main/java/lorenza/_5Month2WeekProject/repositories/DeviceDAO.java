package lorenza._5Month2WeekProject.repositories;

import lorenza._5Month2WeekProject.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceDAO extends JpaRepository<Device, UUID> {

}