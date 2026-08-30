package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Categorie;
import sn.pressingsicap.Demo.Repository.CategorieRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@CacheConfig(cacheNames = "categories")
public class CategorieServiceImpl implements CategorieService{

    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public void saveCategorie(Categorie categorie) {
        categorieRepository.save(categorie);

    }

    @Override
    public Optional<Categorie> getCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    @Override
    public List<Categorie> listCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public void deleteCategorieById(Long id) {
        categorieRepository.deleteById(id);


    }
}
