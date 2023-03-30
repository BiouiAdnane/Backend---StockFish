package com.example.aveiro_project;

import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Enums.TypeEmploye;
import com.example.aveiro_project.Repository.PersonneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class AveiroProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AveiroProjectApplication.class, args);
	}
	@Bean
	CommandLineRunner start(PersonneRepository personneRepository){
		return args -> {
			Stream.of("Adnan", "Ahmed", "Mourad","Othman").forEach(name->{
				Personne personne= new Personne();
				personne.setNom(name);
				personne.setPrenom(name +"aoui");
				personne.setEmail(name+"@gmail.com");
				personne.setTel(19);
				personne.setAdresse("aveiro");
				if (personne.getNom()=="Adnan")
					personne.setTypeEmploye(TypeEmploye.Administrateur);
				else if (personne.getNom()=="Ahmed")
					personne.setTypeEmploye(TypeEmploye.Gestionaire_Emballage);
				else if (personne.getNom()=="Mourad")
					personne.setTypeEmploye(TypeEmploye.Gestionaire_ProduitFini);
				else personne.setTypeEmploye(TypeEmploye.Superviseur);
				personneRepository.save(personne);

			});

		};

} }
