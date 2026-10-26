package sn.pressingsicap.Demo.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Exception.ClientNotFoundException;
import sn.pressingsicap.Demo.Models.Client;
import sn.pressingsicap.Demo.Service.ClientService;

import java.util.List;

@Controller
@RequestMapping(value = "/clients")
@Slf4j
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String creerClient(@ModelAttribute Client client, RedirectAttributes redirectAttributes) {
        try {
            clientService.enregistrerClient(client);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Client enregistré avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Erreur lors de l'enregistrement du client."
            );
        }
        log.info("create the client {} in the database", client.getPrenom());

        return "redirect:/clients/liste";
    }

    @GetMapping(value = "/liste")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String getClientListe(Model model) {
        model.addAttribute("clients", clientService.listerTousLesClients());//Pour la liste
        model.addAttribute("client", new Client());//Pour le formulaire
        log.info("Liste des Client dans la base DD");
         return "clients/liste";
//        return getClientListe().stretoString();

    }

    @GetMapping("/ajouter")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String ajouterFormClient(Model model) {
        model.addAttribute("client", new Client());
        log.info("affichage formulaire Client ");
        return "clients/form-client";
    }

    @GetMapping("/recherche")
    @PreAuthorize("hasRole('GERANT')")
    public ResponseEntity<Client> chercherParTelephone(@RequestParam String telephone) {
        return ResponseEntity.ok(clientService.chercherParTelephone(telephone));
    }



    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")

    public String modifierClient(@PathVariable Long id, Model model) {
        Client client = clientService.modifierClient(id);

        if (client == null){
         throw new ClientNotFoundException("Client introuvable avec l'id : " + id);
        }
        model.addAttribute("client", client);
        log.info("Modification du client {} dans BD" , client.getPrenom());
        return "clients/form-client";
    }

    // Supprimer
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String deleteClient(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            clientService.deleteClient(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Client supprimé avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Impossible de supprimer le client."
            );
        }
        log.info("suppression Id client {} dans la BD", id);
        return "redirect:/clients/liste";
    }
}