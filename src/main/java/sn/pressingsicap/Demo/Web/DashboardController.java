package sn.pressingsicap.Demo.Web;

import javafx.print.PrintColor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sn.pressingsicap.Demo.Repository.*;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final ClientRepository clientRepository;
    private final PaiementRepository paiementRepository;
    private final CommandeRepository commandeRepository;
    private final ArticleRepository articleRepository;
    private final UtilisateurRepository utilisateurRepository;

    ///SecurityContextHolder:C'est l'objet qui permet d'accéder au contexte de sécurité de la requête actuelle.//
   /// SecurityContextHolder.getContext() : → récupère le SecurityContext.
   /// SecurityContextHolder.getContext().getAuthentication(): récupère l'objet Authentication, qui contient les informations sur l'utilisateur connecté.
   /// authentication.getName() : retourne users qui est connecte

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null
                && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getName())) {

            model.addAttribute("username", authentication.getName());
        }

        long nombreClients = clientRepository.count();
        long nombrePaiement = paiementRepository.count();
        long nombreCommandes = commandeRepository.count();
        long nombreArticles = articleRepository.count();
        long nombreutilisateurs = utilisateurRepository.count();

        model.addAttribute("nombreClients", nombreClients);
        model.addAttribute("nombrePaiement", nombrePaiement);
        model.addAttribute("nombreCommandes", nombreCommandes);
        model.addAttribute("nombreArticles", nombreArticles);
        model.addAttribute("nombreutilisateurs", nombreutilisateurs);

        return "dashboard/accueil";
    }


}

