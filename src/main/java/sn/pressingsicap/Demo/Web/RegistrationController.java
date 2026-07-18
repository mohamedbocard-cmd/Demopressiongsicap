package sn.pressingsicap.Demo.Web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sn.pressingsicap.Demo.Models.Utilisateur;
import sn.pressingsicap.Demo.Repository.UtilisateurRepository;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationController {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register") // cette methode permettra de verifier si user est !=null ou present dans BD
    public ResponseEntity<?> registerUser(@RequestBody Utilisateur utilisateur) {
        if (utilisateurRepository.findByUsername(utilisateur.getUsername()).isPresent()){

            return ResponseEntity.badRequest().body("Username already exist");
        }
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));

    }


}
