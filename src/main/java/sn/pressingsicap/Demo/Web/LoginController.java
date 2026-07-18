package sn.pressingsicap.Demo.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
//    private final AuthenticationManager authenticationManager;

    //on va creer une methode pour se loger apres l'inscription


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Utilisateur utilisateur) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        utilisateur.getUsername(),
//                        utilisateur.getPassword()
//                )
//        );
//
//        return ResponseEntity.ok("Connexion réussie");
//    }

