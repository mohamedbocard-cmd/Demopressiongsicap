package sn.pressingsicap.Demo.Service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Utilisateur;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "utilisateurs")

public interface UtilisateurService {

    void saveNewUtilisateur(Utilisateur utilisateur);

    Optional <Utilisateur> getUtilisateurById(Long id);
    List<Utilisateur> listUtilisateur();
    void deleteUtilisateurById(Long id);
}
