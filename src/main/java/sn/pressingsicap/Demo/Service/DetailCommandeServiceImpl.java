package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Categorie;
import sn.pressingsicap.Demo.Models.DetailCommande;
import sn.pressingsicap.Demo.Repository.DetailCommandeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetailCommandeServiceImpl implements DetailCommandeService {

    private DetailCommandeRepository detailCommandeRepository;

    public DetailCommandeServiceImpl(DetailCommandeRepository detailCommandeRepository) {
        this.detailCommandeRepository = detailCommandeRepository;
    }

    @Override
    public void saveDetailCommande(DetailCommande detailCommande) {
        detailCommandeRepository.save(detailCommande);
    }

    @Override
    public Optional<DetailCommande> getDetailCommandeById(Long id) {
        return detailCommandeRepository.findById(id);
    }

    @Override
    public List<DetailCommande> listDetailCommande() {
        return detailCommandeRepository.findAll();
    }

    @Override
    public void deleteDetailCommandeById(Long id) {
        detailCommandeRepository.deleteById(id);
    }
}
