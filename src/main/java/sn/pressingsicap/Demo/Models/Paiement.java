package sn.pressingsicap.Demo.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Builder
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal montantTotal;
    private BigDecimal montantAvance; // Si le client paie une partie au dépôt

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime datePaiement;

    @Column(name = "montant_restant", precision = 12, scale = 2)
    private BigDecimal montantRestant;


    private String modePaiement; // CASH, CARTE, MOBILE_MONEY

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut; // NON_PAYE, AVANCE, PAYE

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;


    // Calcul du montant restant
    @PrePersist
    @PreUpdate
    public void calculerMontantRestant() {

        BigDecimal total = montantTotal != null
                ? montantTotal
                : BigDecimal.ZERO;

        BigDecimal avance = montantAvance != null
                ? montantAvance
                : BigDecimal.ZERO;

        this.montantRestant = total.subtract(avance);
        /// /La méthode subtract() effectue la soustraction entre deux objets BigDecimal.
    }
}

