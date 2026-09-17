package sn.pressingsicap.Demo.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Exception.ClientNotFoundException;
import sn.pressingsicap.Demo.Exception.CommandeNotFoundException;
import sn.pressingsicap.Demo.Exception.PaiementNotFoundException;
import sn.pressingsicap.Demo.Models.Article;
import sn.pressingsicap.Demo.Models.Client;
import sn.pressingsicap.Demo.Models.Commande;
import sn.pressingsicap.Demo.Models.Paiement;
import sn.pressingsicap.Demo.Service.ArticleService;
import sn.pressingsicap.Demo.Service.ClientService;
import sn.pressingsicap.Demo.Service.CommandeService;
import sn.pressingsicap.Demo.Service.PaiementService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/paiements")
@Slf4j
public class PaiementController {
    private final PaiementService paiementService;

    private final CommandeService commandeService;
    private final ClientService clientService;


    public PaiementController(PaiementService paiementService, CommandeService commandeService, ClientService clientService) {
        this.paiementService = paiementService;
        this.commandeService = commandeService;
        this.clientService = clientService;
    }

    @GetMapping("/liste")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERANT')")
    public String listPaiement(Model model) {
        model.addAttribute("paiements",paiementService.listPaiement());//Pour la liste
        model.addAttribute("paiement",new Paiement());//Pour le formulaie

        model.addAttribute("commandes", commandeService.listCommande());//Pour la liste
        model.addAttribute("commande", new Commande());//Pour le formulaire

        model.addAttribute("clients", clientService.listerTousLesClients());//Pour la liste
        model.addAttribute("client", new Client());//Pour le formulaire
        return "paiements/liste";

    }

    @PostMapping("/ajouter")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String ajoutPaiement(@ModelAttribute Paiement paiement, RedirectAttributes redirectAttributes) {
        if (paiement.getDatePaiement() == null) {
            redirectAttributes.addFlashAttribute("error",
                    "La date de paiement est obligatoire.");
            return "redirect:/paiements/liste";
        }

        if (paiement.getDatePaiement().isAfter(LocalDateTime.now())) {
            redirectAttributes.addFlashAttribute("error",
                    "La date de paiement ne peut pas être dans le futur.");
            return "redirect:/paiements/liste";
        }

        paiementService.savePaiement(paiement);

        redirectAttributes.addFlashAttribute("success",
                "Paiement enregistré avec succès.");

        return "redirect:/paiements/liste";
    }

    @GetMapping("/ajouter")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String FormPaiement(Model model) {
        model.addAttribute("paiement", new Paiement());

        model.addAttribute("commandes", commandeService.listCommande());
        model.addAttribute("clients", clientService.listerTousLesClients());

        return "paiements/form-paiement";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String modifierPaiement(@PathVariable Long id, Model model) {

        Paiement paiement = paiementService.getPaiementById(id)
                .orElseThrow(() -> new PaiementNotFoundException("Paiement introuvable"));

        model.addAttribute("paiement", paiement);// formulaire
        model.addAttribute("paiements", paiementService.listPaiement());// liste


        model.addAttribute("clients", clientService.listerTousLesClients()); //liste

        model.addAttribute("commandes", commandeService.listCommande()); //liste

        return "paiements/form-paiement";

    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String deletePaiement(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            paiementService.deletePaiementById(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Paiement supprimé avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Impossible de supprimer le paiement."
            );
        }

        return "redirect:/paiements/liste";
    }
}