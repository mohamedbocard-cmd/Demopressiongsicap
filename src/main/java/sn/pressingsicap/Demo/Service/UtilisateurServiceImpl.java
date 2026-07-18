package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Utilisateur;
import sn.pressingsicap.Demo.Repository.UtilisateurRepository;

import java.util.List;
import java.util.Optional;
@Service
//@RequiredArgsConstructor
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
}
