package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Dto.InscriptionDTO;
import sn.pressingsicap.Demo.Models.Role;
import sn.pressingsicap.Demo.Models.Utilisateur;
import sn.pressingsicap.Demo.Repository.UtilisateurRepository;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@CacheConfig(cacheNames = "utilisateurs")
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    /* Injection via le constructeur*/
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void saveNewUtilisateur(Utilisateur utilisateur) { /* CRYPTAGE DE MDP USERS*/
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateurRepository.saveAndFlush(utilisateur);
    }

    @Override
    public Optional<Utilisateur> getUtilisateurById(Long id) { //recuperation Id user
        return utilisateurRepository.findById(id);
    }

    @Override
    public List<Utilisateur> listUtilisateur() { //Liste des utilisateurs
        return utilisateurRepository.findAll();
    }

    @Override
    public void deleteUtilisateurById(Long id) { //supression Id utilisateur
        utilisateurRepository.deleteById(id);

    }

    @Override
    public void inscrire(InscriptionDTO dto) {
        if (utilisateurRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Cet email existe déjà");
        }
//        if(!passwordEncoder.equals(confirmPassword)) throw new RuntimeException("ERROR");

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setFirstname(dto.getFirstname());
        utilisateur.setLastname(dto.getLastname());
        utilisateur.setUsername(dto.getUsername());

        // IMPORTANT : ne jamais enregistrer le mot de passe en clair
        utilisateur.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        // Rôle
        utilisateur.setRole(dto.getRole());

        utilisateurRepository.saveAndFlush(utilisateur);
    }

    @Override
    public Page<Utilisateur> getAllUserByPage(int page, int size) {
        return utilisateurRepository.findAll(PageRequest.of(page, size));
    }

}
