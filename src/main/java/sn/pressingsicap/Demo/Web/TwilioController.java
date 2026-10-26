
package sn.pressingsicap.Demo.Web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sn.pressingsicap.Demo.Models.TwilioSmsRequest;
import sn.pressingsicap.Demo.Service.SmsService;

@Controller
@RequiredArgsConstructor
public class TwilioController {

    private final SmsService smsService;

    // Afficher le formulaire
    @GetMapping("/sendMessage")
    public String afficherFormulaire(Model model) {
        model.addAttribute(
                "twilioSmsRequest",
                new TwilioSmsRequest()
        );

        return "sendMessage";
    }

    // Envoyer le message WhatsApp
    @PostMapping("/sendMessage")
    public String sendMessage(@Valid @ModelAttribute("twilioSmsRequest") TwilioSmsRequest twilioSmsRequest, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        // Vérifier les erreurs de validation
        if (bindingResult.hasErrors()) {
            return "sendMessage";
        }

        try {
            String status = smsService.sendsms(twilioSmsRequest);

            if ("sent".equalsIgnoreCase(status)
                    || "queued".equalsIgnoreCase(status)) {

                redirectAttributes.addFlashAttribute(
                        "success",
                        "Message WhatsApp envoyé avec succès !"
                );

            } else {
                redirectAttributes.addFlashAttribute(
                        "error",
                        "Échec de l'envoi du message WhatsApp."
                );
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Erreur lors de l'envoi du message WhatsApp."
            );
        }

        return "redirect:/sendMessage";
    }
}
