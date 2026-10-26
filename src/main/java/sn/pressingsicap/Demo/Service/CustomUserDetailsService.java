package sn.pressingsicap.Demo.Service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Utilisateur;
import sn.pressingsicap.Demo.Repository.UtilisateurRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * c'est la classe qui permet de faire Récupération de username verifie s;il existe
     * et il renseigne les credentiel correspondant.
     */
    private  final UtilisateurRepository utilisateurRepository;


    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Utilisateur introuvable"));

        return org.springframework.security.core.userdetails.User
                .withUsername(utilisateur.getUsername())
                .password(utilisateur.getPassword())
                .roles(utilisateur.getRole().name().replace("ROLE_", ""))
                .build();




    }


}
