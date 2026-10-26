package sn.pressingsicap.Demo.Service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Commande;
import sn.pressingsicap.Demo.Models.Paiement;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "paiement")
public interface PaiementService {
    void savePaiement(Paiement paiement);

    Optional<Paiement> getPaiementById(Long id);
    List<Paiement> listPaiement();
    void deletePaiementById(Long id);
}

