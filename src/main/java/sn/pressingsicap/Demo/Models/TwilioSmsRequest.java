package sn.pressingsicap.Demo.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
public class TwilioSmsRequest {
    @NotBlank(message = "Le numéro du destinataire est obligatoire")
    @Pattern(
            regexp = "^\\+[1-9]\\d{7,14}$",
            message = "Le numéro doit être au format international, ex. +221771234567"
    )
         private String toPhoneNumber;

    @NotBlank(message = "Le message est obligatoire")
    @Size(max = 1600, message = "Le message est trop long")
    private String message;

}
