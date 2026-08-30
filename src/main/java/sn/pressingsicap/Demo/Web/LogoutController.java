package sn.pressingsicap.Demo.Web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LogoutController {
  @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {

       // 1. Récupère la session actuelle sans en créer une nouvelle
        HttpSession session = request.getSession(false);
        if (session != null) {
           session.invalidate(); // Tue la session HTTP

        }
        return ResponseEntity.noContent().build();

//      redirectAttributes.addFlashAttribute("message", "Vous êtes bien déconnecté.");



   }

}
