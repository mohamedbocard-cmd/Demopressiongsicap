package sn.pressingsicap.Demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Veuillez saisir un numéro de commande.")
    private String numero_cmd;


    @NotNull(message = "La date de dépôt est obligatoire")
    private LocalDateTime date_Depot;

    @NotNull(message = "La  date_Prevue_Livraison est obligatoire")
    private LocalDateTime date_prevue_Livraison;

    @NotNull(message = "Le statut est obligatoire")
    @Enumerated(EnumType.STRING)
    private StatutCommande statut; // EN_COURS, TERMINEE, LIVREE

    @NotNull(message = "Vous devez sélectionner un client")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<Article> articles;

//    @OneToOne(mappedBy = "commande", cascade = CascadeType.ALL)
//    private Paiement paiement;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiement = new ArrayList<>();


    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true) /// si une article nest plus lie a categorie il va le supprime dans la base.)
    private List<DetailCommande> details = new ArrayList<>();




}
