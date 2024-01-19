package lorenza._5Month2WeekProject.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        @Size(min = 4, max = 30, message = "Il nome deve essere compreso tra i 4 e i 30 caratteri")
        String name,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        @Size(min = 4, max = 30, message = "Il cognome deve essere compreso tra i 4 e i 30 caratteri!")
        String surname,
        @Email(message = "L'indirizzo inserito non è valido")
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        String email,
        @NotEmpty(message = "L'username è un campo obbligatorio!")
        String username) {

}
