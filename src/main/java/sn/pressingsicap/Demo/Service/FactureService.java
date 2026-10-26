package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sn.pressingsicap.Demo.Exception.ClientNotFoundException;
import sn.pressingsicap.Demo.Models.Article;
import sn.pressingsicap.Demo.Models.Client;
import sn.pressingsicap.Demo.Models.Facture;
import sn.pressingsicap.Demo.Models.Utilisateur;
import sn.pressingsicap.Demo.Repository.ClientRepository;
import sn.pressingsicap.Demo.Repository.FactureRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@CacheConfig(cacheNames = "factures")
public interface FactureService {

    void CreerFacture(Facture facture);

    Optional<Facture> getFactureById(Long id);
    List<Facture> listFacture();
    void deleteFactureById(Long id);

    Page<Facture> getAllFactureByPage(int page , int size);


    byte[] genererFacturePdf(Long id);
}
