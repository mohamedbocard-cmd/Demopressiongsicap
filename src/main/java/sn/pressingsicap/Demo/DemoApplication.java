package sn.pressingsicap.Demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sn.pressingsicap.Demo.Models.*;
import sn.pressingsicap.Demo.Repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@SpringBootApplication /*( exclude = {SecurityAutoConfiguration.class})*/
@ComponentScan(basePackages = {"sn.pressingsicap.Demo.Security"})
@ComponentScan(basePackages = {"sn.pressingsicap.Demo.Service"})
@EntityScan(basePackages={"sn.pressingsicap.Demo.Models"})
@ComponentScan(basePackages = {"sn.pressingsicap.Demo.Web"})
@ComponentScan(basePackages = {"sn.pressingsicap.Demo.Repository"})

@EnableWebMvc
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	@Autowired
	private PasswordEncoder passwordEncoder;

	public DemoApplication(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ArticleRepository articleRepository,ClientRepository clientRepository, UtilisateurRepository utilisateurRepository, PaiementRepository paiementRepository, CommandeRepository commandeRepository) {
		return args -> {
			Client client1 =new Client();
					client1.setNom("soumare");
					client1.setPrenom("Mohamed");
					client1.setUsername("dgn@gmail.com");
					client1.setTelephone("874454574");
					client1.setCommande(new ArrayList<>());

			Client client2 = Client.builder()
					.nom("Bocar")
					.prenom("Samba Sakho")
					.telephone("778464054")
					.username("sakho@gmail.com")
					.commande(new ArrayList<>())
					.build();
//
			Commande commande = Commande.builder()
					.date_Depot(LocalDateTime.now())
					.numero_cmd("CMD-8492")
					.date_prevue_Livraison(LocalDateTime.now())
					.statut(StatutCommande.EN_COURS)
					.client(client1)
					.articles(new ArrayList<>())
					.paiement(new ArrayList<>())
					.build();
//
			Paiement paiement = new Paiement();
			paiement.setMontantTotal(BigDecimal.valueOf(15000));
			paiement.setMontantAvance(BigDecimal.valueOf(5000)); // si on a un gros valeur
			paiement.setDatePaiement(LocalDateTime.now()); // date du systeme
			paiement.setModePaiement(paiement.getModePaiement());
			paiement.setStatut(StatutPaiement.AVANCE);
			paiement.setCommande(commande);

			commande.setPaiement(new ArrayList<>());
			paiement.setCommande(commande);

			Article article = new Article();
			article.setNom("Costume 3piece");
			article.setCouleur("Noir");
			article.setDescriptionDefauts("dechirure");
			article.setMarque("nike");
			article.setPrixLavage(BigDecimal.valueOf(8500));
			article.setStatut(StatutArticle.RECU);
			article.setCommande(commande);
//			article.setCategorie(new Categorie());
//
			Utilisateur ut = Utilisateur.builder()
					.nom("Saw")
					.prenom("ba ka")
					.username("Saw@gmail.com")
					.password(passwordEncoder.encode("passer123"))
					.role(Role.ROLE_ADMIN)
					.build();
////
			Utilisateur ut1 = Utilisateur.builder()
					.nom("Tall")
					.prenom("fallou")
					.username("tall@gmail.com")
					.password(passwordEncoder.encode("passer123"))
					.role(Role.ROLE_GERANT)
					.build();

			Utilisateur ut3 = Utilisateur.builder()
					.nom("sarr")
					.prenom("fallou")
					.username("fallou@gmail.com")
					.password(passwordEncoder.encode("passer123"))
					.role(Role.ROLE_EMPLOYER)
					.build();


			Utilisateur ut2 = Utilisateur.builder()
					.nom("Diagana")
					.prenom("fallou")
					.username("diag@gmail.com")
					.password(passwordEncoder.encode("passer123"))
					.role(Role.ROLE_EMPLOYER)
					.build();
//
////
			clientRepository.save(client1);
			clientRepository.save(client2);
			commandeRepository.save(commande);
			paiementRepository.save(paiement);
			utilisateurRepository.save(ut);
			utilisateurRepository.save(ut1);
			utilisateurRepository.save(ut1);
			utilisateurRepository.save(ut3);
			articleRepository.save(article);

////
////
			System.out.println("Client enregistré : " + client1.getNom());
			System.out.println("Client enregistré : " + client2.getNom());
			System.out.println("utilisateur  enregistré : " + ut.getPrenom());
			System.out.println("utilisateur1  enregistré : " + ut1.getPrenom());
			System.out.println("utilisateur2  enregistré : " + ut2.getPrenom());
			System.out.println("utilisateur3  enregistré : " + ut3.getPrenom());
			System.out.println("commande   enregistré succées : " + commande.getId());
			System.out.println("article enregistré succées : " + article.getNom());
			System.out.println("paiement enregistré succées : " + paiement.getId());
////
////
			clientRepository.findAll().forEach(c -> {
				System.out.println(
						c.getId() + " - " +
								c.getNom() + " - " +
								c.getPrenom() + " - " +
								c.getUsername() + " -" +
								c.getTelephone()
				);
			});
////
//		};

		};
	}


}

