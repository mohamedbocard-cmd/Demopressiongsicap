package sn.pressingsicap.Demo.Web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sn.pressingsicap.Demo.Models.Utilisateur;
import sn.pressingsicap.Demo.Repository.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final ClientRepository clientRepository;
    private final PaiementRepository paiementRepository;
    private final CommandeRepository commandeRepository;
    private final ArticleRepository articleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final CategorieRepository categorieRepository;

    /// SecurityContextHolder:C'est l'objet qui permet d'accéder au contexte de sécurité de la requête actuelle.//
    /// SecurityContextHolder.getContext() : → récupère le SecurityContext.
    /// SecurityContextHolder.getContext().getAuthentication(): récupère l'objet Authentication, qui contient les informations sur l'utilisateur connecté.
    /// authentication.getName() : retourne users qui est connecte

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
    /// principal = username si on n'est cote spring boot
        String username = authentication.getName();

        Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsername(username);
        if (utilisateur.isPresent()) {
            model.addAttribute("firstname", utilisateur.get().getFirstname());
        }

        long nombreClients = clientRepository.count();
        long nombrePaiement = paiementRepository.count();
        long nombreCommandes = commandeRepository.count();
        long nombreArticles = articleRepository.count();
        long nombreutilisateurs = utilisateurRepository.count();
        long nombrecategorie = categorieRepository.count();

        model.addAttribute("nombreClients", nombreClients);
        model.addAttribute("nombrePaiement", nombrePaiement);
        model.addAttribute("nombreCommandes", nombreCommandes);
        model.addAttribute("nombreArticles", nombreArticles);
        model.addAttribute("nombreutilisateurs", nombreutilisateurs);
        model.addAttribute("nombrecategorie", nombrecategorie);


        return "dashboard/accueil";
    }
    @GetMapping("/accessDenied")
    public String AccesDenied(){
        return "accessDenied";
    }

//    @PostMapping("/sendMessage")
//    public String SendMessage(ModelAndView model){
//        return "sendMessage";
//    }



}



