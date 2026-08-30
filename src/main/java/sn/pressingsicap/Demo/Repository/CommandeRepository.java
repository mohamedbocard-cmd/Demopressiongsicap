package sn.pressingsicap.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import sn.pressingsicap.Demo.Models.Client;
import sn.pressingsicap.Demo.Models.Commande;
import sn.pressingsicap.Demo.Models.StatutCommande;

import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {


}
