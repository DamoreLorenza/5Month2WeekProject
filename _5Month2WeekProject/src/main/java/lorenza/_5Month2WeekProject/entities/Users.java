package lorenza._5Month2WeekProject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class Users {
        @Id
        @GeneratedValue
        private UUID uuid;
        private String username;
        private String name;
        private String surname;
        private String email;

        public Users(String username, String name, String surname, String email) {
                this.username = username;
                this.name = name;
                this.surname = surname;
                this.email = email;
        }

        @OneToMany(mappedBy = "users")
        @Column(name = "device")
        private Device device;
}
