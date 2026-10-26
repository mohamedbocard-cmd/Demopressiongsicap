package sn.pressingsicap.Demo.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Models.Article;
import sn.pressingsicap.Demo.Models.Client;
import sn.pressingsicap.Demo.Models.Commande;
import sn.pressingsicap.Demo.Models.DetailCommande;
import sn.pressingsicap.Demo.Service.ArticleService;
import sn.pressingsicap.Demo.Service.ClientService;
import sn.pressingsicap.Demo.Service.CommandeService;
import sn.pressingsicap.Demo.Service.DetailCommandeService;

@Controller
@RequestMapping("/detailcommandes")
@Slf4j
public class DetailCommandeController {

    private final DetailCommandeService detailCommandeService;
    private final CommandeService commandeService;
    private final ArticleService articleService;
    private final ClientService clientService;

    public DetailCommandeController(DetailCommandeService detailCommandeService, CommandeService commandeService, ArticleService articleService, ClientService clientService) {
        this.detailCommandeService = detailCommandeService;
        this.commandeService = commandeService;
        this.articleService = articleService;
        this.clientService = clientService;
    }
    /* Liste detailCommande dans BD*/
    @GetMapping("/liste")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERANT')")
    public String listDetailCommande(Model model) {
        model.addAttribute("detailcommandes", detailCommandeService.listDetailCommande());
        model.addAttribute("detailcommande", new DetailCommande());
        model.addAttribute("commandes", commandeService.listCommande());
        model.addAttribute("commande", new Commande()); // Pour formulaire
        //liste et formulaire article
        model.addAttribute("articles", articleService.listArticle()); //la liste
        model.addAttribute("article", new Article()); // Pour formulaire

        model.addAttribute("clients", clientService.listerTousLesClients()); //la liste
        model.addAttribute("client", new Client()); // Pour formulaire

//        return listDetailCommande(model);
        return "detailcommandes/liste";
    }
    /**
     * Backlog: liste de fonctionnalite a developepée
     */


    /**
     * Ajout des donnees dans la BD
     *
     * /@param id
     * /@param redirectAttributes
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String addNewDetailCommande(@ModelAttribute DetailCommande
           detailCommande, RedirectAttributes redirectAttributes) {
        try {
            detailCommandeService.saveDetailCommande(detailCommande);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "detailCommande enregistré avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Erreur lors de l'enregistrement du detailCommande."
            );
        }

        return "redirect:/detailcommandes/liste";
    }
    /**
     * Formulaire DetailCommandes
     * /@param id
     * /@param redirectAttributes
     * @return
     */

    @GetMapping("/ajouter")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String ajouterFormdetailcommande(Model model) {
        model.addAttribute("detailcommande", new DetailCommande());
        model.addAttribute("detailcommandes", detailCommandeService.listDetailCommande());
        model.addAttribute("commandes",commandeService.listCommande());
        model.addAttribute("articles",articleService.listArticle());
        model.addAttribute("clients",clientService.listerTousLesClients());
        return "detailcommandes/form-detailcommande";
    }
    /**
     * Modofication DetailCommandes
     *
     * @param id
     * /@param redirectAttributes
     * @return
     */

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String modifierdetailCommande(@PathVariable Long id, Model model) {

        DetailCommande detailCommande = detailCommandeService.getDetailCommandeById(id)
            .orElseThrow(() -> new RuntimeException("DetailCommande introuvable"));

        model.addAttribute("detailcommande",detailCommande);
        model.addAttribute("detailcommandes", detailCommandeService.listDetailCommande());
        model.addAttribute("commandes", commandeService.listCommande());
        //liste des Articles & le formulaire
        model.addAttribute("articles", articleService.listArticle());

        model.addAttribute("clients", clientService.listerTousLesClients());

        log.info("Modification du DetailCommande {} dans BD" , detailCommande.getCommande());
        return "detailcommandes/form-detailcommande";

    }

    /**
     * Delete DetailCommandes
     *
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String supprimer(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        try {
            detailCommandeService.deleteDetailCommandeById(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "detailcommande supprimé avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Impossible de supprimer le detailcommande."
            );
        }
        log.info("suppression Id detailcommande {} dans la BD", id);

        return "redirect:/detailcommandes/liste";

    }
}
