package sn.pressingsicap.Demo.Service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Article;
import sn.pressingsicap.Demo.Models.Categorie;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "categories")
public interface CategorieService {
    void saveCategorie(Categorie categorie);

    Optional<Categorie> getCategorieById(Long id);
    List<Categorie> listCategorie();
    void deleteCategorieById(Long id);

}
