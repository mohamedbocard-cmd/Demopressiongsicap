package sn.pressingsicap.Demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(name = "nom")
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    @Column(name = "prenom")
    private String prenom;

    @NotBlank(message = "Le telephone est obligatoire")
    @Column(unique = true)
    private String telephone;

    @NotBlank(message = "Le username est obligatoire")
    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commande;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Paiement> paiement;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<DetailCommande> details = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Facture> factures;





}
