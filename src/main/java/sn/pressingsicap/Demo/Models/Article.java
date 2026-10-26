package sn.pressingsicap.Demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.ValidHost;
import org.hibernate.annotations.Audited;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Audited
//@Table(name = "article")
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(name = "nom")
    private String nom; // ex: Costume 3 pièces, Robe de soirée, Couette , chemise,pentallon

    @NotBlank(message = "Le couleur est obligatoire")
    @Column(name = "couleur",length = 45)
    private String couleur;

    @NotBlank(message = "Le marque est obligatoire")
    @Column(name = "marque",length = 10)
    private String marque;

    @NotBlank(message = "La descriptionDefauts est obligatoire")
    @Column(name = "descriptionDefauts",length = 60)
    private String descriptionDefauts; // Pour noter une tache ou une déchirure existante avant lavage

    @Column(name = "prix_Lavage", precision = 12, scale = 2)
    @Min(10)
    @Max(10000)
    private BigDecimal prixLavage;

    @Enumerated(EnumType.STRING)
    private StatutArticle statut; // EN_ATTENTE, EN_COURS_DE_LAVAGE, REPASSE, PRET

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) /// si une article nest plus lie a categorie il va le supprime dans la base.)
    private List<DetailCommande> detailsCommandes  = new ArrayList<>();

}
