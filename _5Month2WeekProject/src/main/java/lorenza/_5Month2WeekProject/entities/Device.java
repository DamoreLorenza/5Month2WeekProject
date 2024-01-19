package lorenza._5Month2WeekProject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "device")
@Getter
@Setter
@ToString
public class Device {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String type;
    private Enum State;

    public Device(String type, Enum State) {
        this.type = type;
        this.State = State;
    }

    @ManyToOne
    @JoinColumn(name = "users")
    private Users users;

}