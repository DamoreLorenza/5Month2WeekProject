package lorenza._5Month2WeekProject.payloads;

import jakarta.validation.constraints.NotEmpty;

public record NewDeviceDTO (
        @NotEmpty(message = "Il tipo è un campo obbligatorio!")
        String type,
        @NotEmpty(message = "Lo state è un campo obbligatorio!")
        Enum state){
}