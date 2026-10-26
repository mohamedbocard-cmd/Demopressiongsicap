package sn.pressingsicap.Demo.config;

import com.twilio.Twilio;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@Data
@Getter @Setter
@EnableConfigurationProperties
public class TwilioInitializer {
    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        log.info("twilio intialized with accountSid: "+twilioConfiguration.getAccountSid());
    }


}
