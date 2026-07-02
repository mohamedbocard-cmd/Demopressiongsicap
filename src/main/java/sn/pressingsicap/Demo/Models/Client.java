package sn.pressingsicap.Demo.Models;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.List;
@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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

    @NotBlank(message = "Le email est obligatoire")
    private String email;

    @ToString.Include
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandes;
}
