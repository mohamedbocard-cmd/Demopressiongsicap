package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Facture;
import sn.pressingsicap.Demo.Repository.FactureRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@CacheConfig(cacheNames = "factures")
@Data
public class FactureServiceImpl implements FactureService{

    private final FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public void CreerFacture(Facture facture) {
        factureRepository.save(facture);

    }

    @Override
    public Optional<Facture> getFactureById(Long id) {
        return factureRepository.findById(id);

    }

    @Override
    public List<Facture> listFacture() {
        return factureRepository.findAll();
    }

    @Override
    public void deleteFactureById(Long id) {
        factureRepository.deleteById(id);

    }

}
