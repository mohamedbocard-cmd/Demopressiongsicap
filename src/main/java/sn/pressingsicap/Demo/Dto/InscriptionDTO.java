package sn.pressingsicap.Demo.Dto;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter@NoArgsConstructor @AllArgsConstructor
@Builder
public class InscriptionDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String confirmPassword;

}
