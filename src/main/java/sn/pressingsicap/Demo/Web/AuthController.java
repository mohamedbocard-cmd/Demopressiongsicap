//package sn.pressingsicap.Demo.Web;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class AuthController {
//
//    @PostMapping(value = "/logout")
//    public String logout(HttpServletRequest request) {
//
//        // 1. Récupère la session actuelle sans en créer une nouvelle
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate(); // Tue la session HTTP
//        }
//
//        // 2. Efface les données d'authentification du thread actuel
//        SecurityContextHolder.clearContext();
//
//        // 3. Redirige l'utilisateur vers la page de connexion
//        return "redirect:/login?logout";
//    }
//}
//
