package sn.pressingsicap.Demo.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sn.pressingsicap.Demo.Exception.ArticleNotFoundException;
import sn.pressingsicap.Demo.Models.Article;
import sn.pressingsicap.Demo.Models.StatutArticle;
import sn.pressingsicap.Demo.Service.ArticleService;
import sn.pressingsicap.Demo.Service.CategorieService;
import sn.pressingsicap.Demo.Service.CommandeService;


@Controller
@RequestMapping(value = "/articles")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    private final CommandeService commandeService;
    private final CategorieService categorieService;

    public ArticleController(ArticleService articleService, CommandeService commandeService, CategorieService categorieService) {
        this.articleService = articleService;
        this.commandeService = commandeService;
        this.categorieService = categorieService;
    }

    @GetMapping(path = "/liste")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT','EMPLOYER')")
    public String listArticle(Model model) {
        model.addAttribute("commandes", commandeService.listCommande());//Pour la liste
        model.addAttribute("article", new Article());
        model.addAttribute("articles", articleService.listArticle());

        model.addAttribute("categories", categorieService.listCategorie());//Pour la liste


        return "articles/liste";
    }


    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT','EMPLOYER')")
    public String addNewArticle(@ModelAttribute Article article, RedirectAttributes redirectAttributes) {
        if (article.getStatut() == null) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Le statut de l'article est obligatoire."
            );

            return "redirect:/articles/liste";
        }

        if (article.getStatut() == StatutArticle.EN_COURS_DE_NETTOYAGE) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Un nouvel article ne peut pas être directement livré."
            );

            return "redirect:/articles/liste";
        }

        articleService.saveArticle(article);

        redirectAttributes.addFlashAttribute(
                "success",
                "Article enregistré avec succès."
        );

        return "redirect:/articles/liste";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT','EMPLOYER')")
    public String modifierArticle(@PathVariable Long id, Model model) {

        Article article = articleService.getArticleById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article introuvable"));

        model.addAttribute("article", article);
        model.addAttribute("commandes", commandeService.listCommande());//Pour la liste

        model.addAttribute("categories", categorieService.listCategorie());//Pour la liste


        log.info("Modification l'article {} dans BD", article.getNom());

        return "articles/form-article.html";


    }

    @GetMapping("/ajouter")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT','EMPLOYER')")
    public String ajouterFormArticle(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("commandes", commandeService.listCommande());
        model.addAttribute("categories", categorieService.listCategorie());//Pour la liste


        log.info("affichage formulaire article ");
        return "articles/form-article";
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','GERANT','EMPLOYER')")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            articleService.deleteArticleById(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Article supprimé avec succès !"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Impossible de supprimer le article."
            );
        }
        return "redirect:/articles/liste";
    }


}

