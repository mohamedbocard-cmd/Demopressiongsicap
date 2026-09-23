package sn.pressingsicap.Demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.core.instrument.config.MeterFilter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "facture")
@EntityListeners(AuditingEntityListener.class)
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT")
    private LocalDateTime dateCreation;

    @Column(name = "statutPaiement")
    private String statutPaiement;

    @Column
    private BigDecimal montant_total;

    private int quantite;
    private BigDecimal prix;

    private String numeroFacture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "commande_id", unique = true)
    private Commande commande;

    /*@PrePersist: pour exécuter automatiquement une méthode juste avant*/
    // qu'une entité ne soit enregistrée
    // pour la première fois dans la base de données.

   /// @PreUpdate: Il s'agit d'une fonctionnalité qui vous permet d' interrompre l'exécution de code de temps à autre
    ///avant la mise à jour de l'opération de base de données .


    @PrePersist
    @PreUpdate
    public void calculerMontantTotal() {

        BigDecimal prixUnitaire =
                prix != null ? prix : BigDecimal.ZERO;

        montant_total = prixUnitaire.multiply(
                BigDecimal.valueOf(quantite)
        );
    }
}



