package sn.pressingsicap.Demo.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // ex: Costume 3 pièces, Robe de soirée, Couette , chemise,pentallon
    private String couleur;
    private String marque;
    private String descriptionDefauts; // Pour noter une tache ou une déchirure existante avant lavage

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
