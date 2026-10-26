package sn.pressingsicap.Demo.Web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Dto.InscriptionDTO;
import sn.pressingsicap.Demo.Repository.UtilisateurRepository;
import sn.pressingsicap.Demo.Service.UtilisateurService;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private  final UtilisateurService utilisateurService;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/inscription")
    public String afficherInscription(Model model) {

        model.addAttribute("inscription", new InscriptionDTO());

        return "inscription";
    }


    @PostMapping("/inscription")
    public String inscrire(
            @ModelAttribute("inscription") InscriptionDTO dto,
            RedirectAttributes redirectAttributes) {

        try {

            utilisateurService.inscrire(dto);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Inscription réussie ! Vous pouvez maintenant vous connecter."
            );

            return "redirect:/login";

        } catch (RuntimeException e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );

            return "redirect:/inscription";
        }
    }
    }

