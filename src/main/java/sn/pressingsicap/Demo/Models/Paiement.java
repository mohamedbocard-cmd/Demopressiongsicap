package sn.pressingsicap.Demo.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal montantTotal;
    private BigDecimal montantAvance; // Si le client paie une partie au dépôt
    private LocalDateTime datePaiement;

    private String modePaiement; // CASH, CARTE, MOBILE_MONEY

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut; // NON_PAYE, AVANCE, PAYE

    @OneToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
}
