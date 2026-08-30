package sn.pressingsicap.Demo.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Exception.ArticleNotFoundException;
import sn.pressingsicap.Demo.Exception.CategorieNotFoundException;
import sn.pressingsicap.Demo.Exception.ClientNotFoundException;
import sn.pressingsicap.Demo.Models.Article;
import sn.pressingsicap.Demo.Models.Categorie;
import sn.pressingsicap.Demo.Models.Client;
import sn.pressingsicap.Demo.Service.CategorieService;
import sn.pressingsicap.Demo.Service.ClientService;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping(value = "/categories")
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String creerCategorie(@ModelAttribute Categorie categorie, RedirectAttributes redirectAttributes) {
        try {
            categorieService.saveCategorie(categorie);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Categories enregistré avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Erreur lors de l'enregistrement du categories."
            );
        }
        log.info("create the categorie {} in the database", categorie.getNom());

        return "redirect:/categories/liste";
    }

    @GetMapping(value = "/liste")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String getCategorieListe(Model model) {
        model.addAttribute("categories", categorieService.listCategorie());//Pour la liste
        model.addAttribute("categorie", new Categorie());//Pour le formulaire
        log.info("Liste des Categorie dans la base DD");
        return "categories/liste";
//        return getClientListe().stretoString();

    }

    @GetMapping("/ajouter")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String ajouterFormCategorie(Model model) {
        model.addAttribute("categorie", new Categorie());
        log.info("affichage formulaire Categorie ");
        return "categories/form-categorie";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")

    public String modifierCategorie(@PathVariable Long id, Model model) {
        Categorie categorie = categorieService.getCategorieById(id)
                .orElseThrow(() -> new CategorieNotFoundException("Categorie introuvable"));

        model.addAttribute("categorie", categorie);
        log.info("Modification du categorie {} dans BD" , categorie.getNom());
        return "categories/form-categorie";
    }
    // Supprimer
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String deleteCategorie(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            categorieService.deleteCategorieById(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Categorie supprimé avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Impossible de supprimer le categorie."
            );
        }
        log.info("suppression Id categorie {} dans la BD", id);
        return "redirect:/categories/liste";
    }
}
