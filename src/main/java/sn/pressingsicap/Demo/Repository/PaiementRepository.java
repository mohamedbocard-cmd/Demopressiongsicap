package sn.pressingsicap.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import sn.pressingsicap.Demo.Models.Paiement;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {
    // Calcule le reste pour un paiement spécifique
    @Query(value = "SELECT (p.montantTotal - p.montantAvance) FROM Paiement p WHERE p.id = :id")
    Double getMontantRestantById(@Param("id") Long id);

    // Calcule la somme restante globale pour un client donné
    @Query("SELECT SUM(p.montantTotal) - SUM(COALESCE(p.montantAvance, 0)) FROM Paiement p WHERE p.Id = :Id")
    Double getTotalDuParClient(@Param("Id") Long Id);
}
