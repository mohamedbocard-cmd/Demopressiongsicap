package sn.pressingsicap.Demo.Models;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Builder
public class DetailCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int quantité;
    @NotBlank
    @Column(name = "prixUnitaire")
    private BigDecimal prixUnitaire;

    @Column
    private BigDecimal montant_total;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;


    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     *
     */

    @PrePersist
    @PreUpdate
    public void calculerMontantTotal() {

        BigDecimal prixUni =
                prixUnitaire != null ? prixUnitaire : BigDecimal.ZERO;

        montant_total = prixUnitaire.multiply(
                BigDecimal.valueOf(quantité)
        );
    }
}

