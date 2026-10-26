package sn.pressingsicap.Demo.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sn.pressingsicap.Demo.Models.TwilioSmsRequest;
import sn.pressingsicap.Demo.config.TwilioConfiguration;

@Service
@Data
public class SmsService {
    private final TwilioConfiguration twilioConfiguration;

    public SmsService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    //send message to number
    public String sendsms(@RequestBody TwilioSmsRequest twilioSmsRequest)
    {

        Message message = Message.creator(
                new PhoneNumber(twilioSmsRequest.getToPhoneNumber()),
                new PhoneNumber(twilioConfiguration.getFromNumber()),
                twilioSmsRequest.getMessage()
        ).create(); /// envoie la requête à Twilio.

        return message.getStatus().toString();


    }
    }
