package sn.pressingsicap.Demo.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.pressingsicap.Demo.Models.Utilisateur;
import sn.pressingsicap.Demo.Service.UtilisateurService;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/admin")

public class UtilisateurController {

    @Autowired
    private  final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    /*
    Lise des Users dans la base
    **/
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Utilisateur> listUtilisateur() {
        log.info("listUtilisateur{} in the database", utilisateurService.listUtilisateur());
        return utilisateurService.listUtilisateur();

    }

    @GetMapping("/ajouter")
    @PreAuthorize("hasRole('ADMIN')")
    public String ajouterUtilisateur(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "admin/form-utilisateur";
    }
    /**
     * Ajout du Users
     * */
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String addNewUtilisateure(@ModelAttribute Utilisateur utilisateur) {

        utilisateurService.saveNewUtilisateur(utilisateur);

        return "redirect:/admin/utilisateurs";
    }
    /**
     * Recuperation du Users via son identifiant (id)
     * */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String modifierUtilisateur(@PathVariable Long id, Model model) {

        Utilisateur utilisateur = utilisateurService
                .getUtilisateurById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        model.addAttribute("utilisateur", utilisateur);

        return "admin/form-utilisateur";
    }

    /**
     * suppression Id Users
     * */


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUtilisateurById(@PathVariable Long id) {
        log.info("deleteUtilisateur {} ",id);
        utilisateurService.deleteUtilisateurById(id);
    }
}
