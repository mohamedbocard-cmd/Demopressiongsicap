//package sn.pressingsicap.Demo.Web;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@Slf4j
//public class MessageController {
//
//    @PostMapping("/sendMessage")
//    public String handleSendMessage(@RequestParam("fromPhoneNumber") String fromPhoneNumber,
//                                    @RequestParam("message") String message,
//                                    RedirectAttributes redirectAttributes) {
//
//        ///  Ici, vous vous connectez à votre service pour transférer le message.
//        log.info("Numéro: " + fromPhoneNumber);
//       log.info("Message: " + message);
//
//        redirectAttributes.addFlashAttribute("success", "Le message a été transmis avec succès.!");
//
//        return "redirect:/sendMessage";
//    }
//}
