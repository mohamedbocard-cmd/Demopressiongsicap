package sn.pressingsicap.Demo.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Exception.UtilisateurNotFoundException;
import sn.pressingsicap.Demo.Models.Utilisateur;
import sn.pressingsicap.Demo.Service.UtilisateurService;


@Controller
@Slf4j
@RequestMapping("/admin")

public class UtilisateurController {

    private  final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    /*
    Lise des Users dans la base
    **/
    @GetMapping("/liste")
    @PreAuthorize("hasRole('ADMIN')")
    public String getUtilisateur (Model model) {
        model.addAttribute("utilisateurs", utilisateurService.listUtilisateur());//Pour la liste
        model.addAttribute("utilisateur", new Utilisateur());//Pour le formulaire

        return "admin/liste";
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
    public String addNewUtilisateure(@ModelAttribute Utilisateur utilisateur, RedirectAttributes redirectAttributes) {
        try {
            utilisateurService.saveNewUtilisateur(utilisateur);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Utilisateur enregistré avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Erreur lors de l'enregistrement du utilisateur."
            );
        }
        return "redirect:/admin/liste";


    }
    /**
     * Modification du Users via son identifiant (id)
     * */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String modifierUtilisateur(@PathVariable Long id, Model model) {

        Utilisateur utilisateur = utilisateurService
                .getUtilisateurById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable"));

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("utilisateurs", utilisateurService.listUtilisateur());

        return "admin/form-utilisateur";

    }

    /**
     * suppression Id Users
     * */


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUtilisateur(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            utilisateurService.deleteUtilisateurById(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Utilisateur supprimé avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Impossible de supprimer le utilisateur."
            );
        }
        return "redirect:/admin/liste";
    }
}
