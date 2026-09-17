package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Dto.InscriptionDTO;
import sn.pressingsicap.Demo.Models.Role;
import sn.pressingsicap.Demo.Models.Utilisateur;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@CacheConfig(cacheNames = "utilisateurs")

public interface UtilisateurService {

    void saveNewUtilisateur(Utilisateur utilisateur);

    Optional <Utilisateur> getUtilisateurById(Long id);
    List<Utilisateur> listUtilisateur();
    void deleteUtilisateurById(Long id);

        public void inscrire(InscriptionDTO dto);
}
