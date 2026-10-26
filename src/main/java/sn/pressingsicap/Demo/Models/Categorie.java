package sn.pressingsicap.Demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString
@Table(name = "categories")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Le nom est obligatoire")
    private String nom;

    @OneToMany(mappedBy = "categorie")
    private List<Article> articles = new ArrayList<>();
}
