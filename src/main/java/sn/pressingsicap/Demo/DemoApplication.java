package sn.pressingsicap.Demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sn.pressingsicap.Demo.Models.Client;
import sn.pressingsicap.Demo.Models.Role;
import sn.pressingsicap.Demo.Models.Utilisateur;
import sn.pressingsicap.Demo.Repository.ClientRepository;
import sn.pressingsicap.Demo.Repository.UtilisateurRepository;


@SpringBootApplication /*( exclude = {SecurityAutoConfiguration.class})*/
@ComponentScan(basePackages = {"sn.pressingsicap.Demo.Security"})
@ComponentScan(basePackages = {"sn.pressingsicap.Demo.Service"})
@EntityScan(basePackages={"sn.pressingsicap.Demo.Models"})
@EnableWebMvc
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	@Autowired
	private  PasswordEncoder passwordEncoder;

    public DemoApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(ClientRepository clientRepository, UtilisateurRepository utilisateurRepository){
		return args -> {
			Client client1 = Client.builder()
					.nom("Diagana")
					.prenom("Mohamed")
					.telephone("7854871454")
					.email("dgn@gmail.com")
					.build();

			Utilisateur ut = Utilisateur.builder()
					.nom("Saw")
					.prenom("ba ka")
					.username("Saw@gmail.com")
					.password(passwordEncoder.encode("passer123"))
					.role(Role.ROLE_ADMIN)
					.build();


			Utilisateur ut1 = Utilisateur.builder()
					.nom("Tall")
					.prenom("fallou")
					.username("tall@gmail.com")
					.password(passwordEncoder.encode("passer123"))
					.role(Role.ROLE_GERANT)
					.build();

			Client client2 = Client.builder()
					.nom("Bocar")
					.prenom("Samba Sakho")
					.telephone("778464054")
					.email("sakho@gmail.com")
					.build();

			clientRepository.save(client1);
			clientRepository.save(client2);
			utilisateurRepository.save(ut);
			utilisateurRepository.save(ut1);

			System.out.println("Client enregistré : " + client1.getNom());
			System.out.println("Client enregistré : " + client2.getNom());
			System.out.println("utilisateur1  enregistré : " + ut.getPrenom());
			System.out.println("utilisateur2  enregistré : " + ut.getPrenom());

			clientRepository.findAll().forEach(c -> {
				System.out.println(
						c.getId() + " - " +
								c.getNom() + " - " +
								c.getPrenom() + " - " +
								c.getEmail() + " -" +
								c.getTelephone()
				);
			});

		};

	}

}

