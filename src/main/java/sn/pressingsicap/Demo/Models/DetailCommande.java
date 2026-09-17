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
@ToString
@Builder
public class DetailCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer quantité;
    @NotBlank(message = "prixUnitaire est obligatoire ")
    @Column(name = "prixUnitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "total")
    private BigDecimal total;

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
     * this.prixUnitaire == null || vide il va retourne 0 au lieu erreure
     * */
    public BigDecimal getMontanttotal() {
        if (this.prixUnitaire == null) {
            return BigDecimal.ZERO;
        }
        // Conversion de la quantité en BigDecimal puis multiplication
        return this.prixUnitaire.multiply(BigDecimal.valueOf(this.quantité));
    }

}
