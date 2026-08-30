package sn.pressingsicap.Demo.Web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor  /** injection dans le model ou @Data , ou Getter , Setter ou constructeur avec argument*/
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}