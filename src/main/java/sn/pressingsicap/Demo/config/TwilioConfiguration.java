package sn.pressingsicap.Demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Getter
@Setter
@Data
@Configuration
@ConfigurationProperties(prefix = "twilio")

public class TwilioConfiguration {

    private String accountSid;

    private String authToken;

    private String fromNumber; ///numéro Twilio de l'expéditeur.
}