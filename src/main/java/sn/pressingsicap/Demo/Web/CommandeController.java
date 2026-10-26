package sn.pressingsicap.Demo.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Exception.CommandeNotFoundException;
import sn.pressingsicap.Demo.Models.*;
import sn.pressingsicap.Demo.Service.ArticleService;
import sn.pressingsicap.Demo.Service.ClientService;
import sn.pressingsicap.Demo.Service.CommandeService;
import sn.pressingsicap.Demo.Service.PaiementService;

@Controller
@Slf4j
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    private final ClientService clientService;


    public CommandeController(CommandeService commandeService, ClientService clientService, ArticleService articleService, PaiementService paiementService) {
        this.commandeService = commandeService;
        this.clientService = clientService;
    }

    /*
    Lise des commande dans la base
    **/
    @GetMapping("/liste")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String listCommande(Model model) {
        model.addAttribute("commandes",commandeService.listCommande());//Pour la liste

        model.addAttribute("commande", new Commande());//Pour le formulaire
        model.addAttribute("clients", clientService.listerTousLesClients());
        model.addAttribute("client", new Client());//Pour le formulaire



        return "commandes/liste";
    }

    /**
     * Ajout du Commande
     *
     */
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String addNewCommande(@ModelAttribute Commande commande, RedirectAttributes redirectAttributes) {
        try {
            commandeService.saveCommande(commande);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Commande enregistré avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Erreur lors de l'enregistrement du commande."
            );
        }

        return "redirect:/commandes/liste";
    }
    @GetMapping("/ajouter")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String ajouterFormCommande(Model model) {
        model.addAttribute("commande", new Commande());
        model.addAttribute("clients",clientService.listerTousLesClients());
        return "commandes/form-commande";
    }


    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String modifierCommande(@PathVariable Long id, Model model) {

        Commande commande = commandeService.getCommandeById(id)
                .orElseThrow(() -> new CommandeNotFoundException("Commande introuvable"));

        model.addAttribute("commande", commande);
        model.addAttribute("commandes", commandeService.listCommande());
        model.addAttribute("clients", clientService.listerTousLesClients());
        log.info("Modification du Commande {} dans BD" , commande.getNumero_cmd());
        return "commandes/form-commande";

    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String supprimer(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        try {
            commandeService.deleteCommandeById(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Commande supprimé avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Impossible de supprimer le commande."
            );
        }
        log.info("suppression Id commande {} dans la BD", id);

        return "redirect:/commandes/liste";

    }

}
