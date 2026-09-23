package sn.pressingsicap.Demo.Web;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Exception.ArticleNotFoundException;
import sn.pressingsicap.Demo.Models.Article;
import sn.pressingsicap.Demo.Models.Facture;
import sn.pressingsicap.Demo.Models.StatutArticle;
import sn.pressingsicap.Demo.Models.StatutPaiement;
import sn.pressingsicap.Demo.Service.ClientService;
import sn.pressingsicap.Demo.Service.CommandeService;
import sn.pressingsicap.Demo.Service.FactureService;

import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/factures")
@Data
public class FactureController {
    private final FactureService factureService;
    private final ClientService clientService;
    private final CommandeService commandeService;

    public FactureController(FactureService factureService, ClientService clientService, CommandeService commandeService) {
        this.factureService = factureService;
        this.clientService = clientService;
        this.commandeService = commandeService;
    }

    @GetMapping("/liste")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String listFacture(Model model) {
        model.addAttribute("factures", factureService.listFacture());//Pour la liste
        model.addAttribute("facture", new Facture());
        model.addAttribute("clients", clientService.listerTousLesClients());
        model.addAttribute("commandes", commandeService.listCommande());
        log.info("getAll liste facture");

        return "factures/liste";
    }

    @PostMapping("/ajouter")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String addNewFacture(@ModelAttribute Facture facture, RedirectAttributes redirectAttributes) {
        try {
            factureService.CreerFacture(facture);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Facture enregistré avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Erreur lors de l'enregistrement du Facture."
            );
        }
        log.info("create the Facture {} in the database", facture.getNumeroFacture());

        return "redirect:/factures/liste";

    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String modifierFacture(@PathVariable Long id, Model model) {

        Facture facture = factureService.getFactureById(id)
                .orElseThrow(() -> new RuntimeException("Facture introuvable"));

        model.addAttribute("facture", facture);
        model.addAttribute("clients", clientService.listerTousLesClients());//Pour la liste
        model.addAttribute("commandes", commandeService.listCommande());//Pour la liste


        log.info("Modification factures {} dans BD", facture.getId());

        return "factures/form-facture";

    }
    ///log.info(categories.toString());*/
    @GetMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String ajouterFormfacture(Model model) {
        model.addAttribute("facture", new Facture());
        model.addAttribute("clients", clientService.listerTousLesClients());//Pour la liste
        model.addAttribute("commandes", commandeService.listCommande());//Pour la liste

        log.info("affichage formulaire facture ");
        return "factures/form-facture";

    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String deleteFacture(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            factureService.deleteFactureById(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Facture supprimé avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Impossible de supprimer le facture."
            );
        }
        return "redirect:/factures/liste";

    }

}