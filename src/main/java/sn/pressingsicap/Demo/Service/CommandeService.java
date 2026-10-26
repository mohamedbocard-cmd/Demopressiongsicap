package sn.pressingsicap.Demo.Service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Commande;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "commandes")
public interface CommandeService {

    void saveCommande(Commande commande);

    Optional<Commande> getCommandeById(Long id);
    List<Commande> listCommande();
    void deleteCommandeById(Long id);
}
