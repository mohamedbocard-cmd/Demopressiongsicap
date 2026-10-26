package sn.pressingsicap.Demo.Web;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Models.*;
import sn.pressingsicap.Demo.Service.ArticleService;
import sn.pressingsicap.Demo.Service.ClientService;
import sn.pressingsicap.Demo.Service.CommandeService;
import sn.pressingsicap.Demo.Service.FactureService;


@Controller
@Slf4j
@RequestMapping(value = "/factures")
@Data
public class FactureController {
    private final FactureService factureService;
    private final ClientService clientService;
    private final CommandeService commandeService;
    private final ArticleService articleService;

    public FactureController(FactureService factureService, ClientService clientService, CommandeService commandeService, ArticleService articleService) {
        this.factureService = factureService;
        this.clientService = clientService;
        this.commandeService = commandeService;
        this.articleService = articleService;
    }

    @GetMapping("/liste")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT')")
    public String listFacture(ModelMap modelMap,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "4") int size
    ) {
        Page<Facture> factures = factureService.getAllFactureByPage(page, size);
        modelMap.addAttribute("factures", factures);
        modelMap.addAttribute("currentPage", page);// affiche la page en cours qui est la page {0}
        modelMap.addAttribute("pages", new int[factures.getTotalPages()]);

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
        model.addAttribute("articles", articleService.listArticle());//Pour la liste


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
        model.addAttribute("articles", articleService.listArticle());//Pour la liste

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

/// Generation fichier PDF en cliquant sur le boutton PDF
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> genererPdf(
            @PathVariable Long id) {

        try {

            byte[] pdf = factureService.genererFacturePdf(id);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=facture-" + id + ".pdf"
                    )
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (Exception e) {

            return ResponseEntity.internalServerError()
                    .build();
        }
    }
}