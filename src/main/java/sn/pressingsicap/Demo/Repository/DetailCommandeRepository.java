package sn.pressingsicap.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import sn.pressingsicap.Demo.Models.DetailCommande;

@Repository
@EnableJpaRepositories
public interface DetailCommandeRepository extends JpaRepository<DetailCommande,Long> {
}
