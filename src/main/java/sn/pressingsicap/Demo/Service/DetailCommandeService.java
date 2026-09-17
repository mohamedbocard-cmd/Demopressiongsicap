package sn.pressingsicap.Demo.Service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Commande;
import sn.pressingsicap.Demo.Models.DetailCommande;

import java.util.List;
import java.util.Optional;

@Service
public interface DetailCommandeService {
    void saveDetailCommande(DetailCommande detailCommande);

    Optional<DetailCommande> getDetailCommandeById(Long id);
    List<DetailCommande> listDetailCommande();
    void deleteDetailCommandeById(Long id);
}
