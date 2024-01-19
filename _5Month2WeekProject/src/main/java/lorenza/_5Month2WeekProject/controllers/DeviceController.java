package lorenza._5Month2WeekProject.controllers;

import lorenza._5Month2WeekProject.entities.Device;
import lorenza._5Month2WeekProject.exception.BadRequestException;
import lorenza._5Month2WeekProject.payloads.NewDeviceDTO;
import lorenza._5Month2WeekProject.payloads.NewDeviceResponseDTO;
import lorenza._5Month2WeekProject.payloads.NewUserResponseDTO;
import lorenza._5Month2WeekProject.services.DeviceService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public Page<Device> getUsers(@RequestParam int page, @RequestParam int size, @RequestParam String orderBy) {
        return deviceService.getDevice(page, size, orderBy);
    }

    @GetMapping("/{deviceUUID}")
    public Device getDeviceByUUID(@PathVariable UUID deviceUUID) {
        return deviceService.findById(deviceUUID);
    }

    @PutMapping("/{deviceUUID}")
    public Device getDeviceByUUIDAndUpdate(@PathVariable UUID deviceUUID, @RequestBody Device modifiedDevicePayload) {
        return deviceService.findByIdAndUpdate(deviceUUID, modifiedDevicePayload);
    }

    @DeleteMapping("/{deviceUUID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getDeviceByUUIDAndDelete(@PathVariable UUID deviceUUID) {
        deviceService.findByIdAndDelete(deviceUUID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDeviceResponseDTO createDevice(@RequestBody @Validated NewDeviceDTO newDevicePayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload");
        } else {
            Device newDevice = deviceService.save(newDevicePayload);

        }
        return new NewUserResponseDTO(newDevicePayload.getUUID());
    }
}

