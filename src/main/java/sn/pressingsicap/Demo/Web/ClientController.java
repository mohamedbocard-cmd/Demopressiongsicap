package sn.pressingsicap.Demo.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.pressingsicap.Demo.Models.Client;
import sn.pressingsicap.Demo.Service.ClientService;

import java.util.List;

@Controller
@RequestMapping(value = "/clients")
@Slf4j
public class ClientController {
    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/enregistrer")
    public String creerClient(@ModelAttribute Client client) {

        clientService.enregistrerClient(client);

        return "redirect:/clients/liste";
    }

    @GetMapping(value = "/liste")
    @PreAuthorize("hasRole('GERANT')")
    public String getClientListe(Model model) {
        model.addAttribute("clients", clientService.listerTousLesClients());//Pour la liste
        model.addAttribute("client", new Client());//Pour le formulaire
         return "clients/liste";
//        return getClientListe().stretoString();

    }

    @GetMapping("/recherche")
    @PreAuthorize("hasRole('GERANT')")
    public ResponseEntity<Client> chercherParTelephone(@RequestParam String telephone) {
        return ResponseEntity.ok(clientService.chercherParTelephone(telephone));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Client> modifierClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return ResponseEntity.ok(clientService.modifierClient(id, clientDetails));
    }
}