package sn.pressingsicap.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.pressingsicap.Demo.Models.Categorie;
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
