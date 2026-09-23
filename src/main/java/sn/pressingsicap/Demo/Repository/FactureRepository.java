package sn.pressingsicap.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.pressingsicap.Demo.Models.Facture;

import java.math.BigDecimal;

@Repository
public interface FactureRepository extends JpaRepository<Facture ,Long> {
}

