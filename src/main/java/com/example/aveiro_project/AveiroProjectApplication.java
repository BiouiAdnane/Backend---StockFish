package com.example.aveiro_project;

import com.example.aveiro_project.Entities.*;
import com.example.aveiro_project.Enums.*;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Repository.*;
import org.hibernate.query.sqm.ConstructorEntityArgumentMode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class AveiroProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AveiroProjectApplication.class, args);
	}
	@Bean
	CommandLineRunner start(PersonneRepository personneRepository,
							DepotRepository depotRepository,
							OperationRepository operationRepository,
							ArticleRepository articleRepository,
							FamilleRepo familleRepo){
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

			// FAMILLES
			Ingredient ingredient1 =new Ingredient();
			ingredient1.setNom("huile_d'olive");
			Ingredient ingredient2 =new Ingredient();
			ingredient2.setNom("sauce_tomate");
			Marque marque = new Marque();
			marque.setNom("Propre");
			Marque marque1 =new Marque();
			marque1.setNom("Client");
			Nature nature=new Nature();
			nature.setNom("Sardines");
			Nature nature1=new Nature();
			nature1.setNom("Maquereaus");
			Qualite qualite = new Qualite();
			qualite.setNom("Standard");
			Qualite qualite1=new Qualite();
			qualite1.setNom("Marchand");
			Qualite qualite2=new Qualite();
			qualite2.setNom("Local");
			familleRepo.save(ingredient1);
			familleRepo.save(ingredient2);
			familleRepo.save(marque);
			familleRepo.save(marque1);
			familleRepo.save(nature);
			familleRepo.save(nature1);
			familleRepo.save(qualite1);
			familleRepo.save(qualite);
			familleRepo.save(qualite2);


//LES ARTICLES

			Article Joly =new Article();
			Joly.setIngredient(ingredient1);
			Joly.setNature(nature1);
			Joly.setQualite(qualite1);
			Joly.setMarque(marque1);
			Joly.setDesigniation("HO JOLLY MAQ BM MarPr");
			Joly.setQuantite_Article(0);
			articleRepository.save(Joly);

			Article Jolly =new Article();
			Jolly.setIngredient(ingredient2);
			Jolly.setNature(nature);
			Jolly.setQualite(qualite);
			Jolly.setMarque(marque);
			Jolly.setDesigniation("HV JOLLY MAQ BM MarPr");
			Jolly.setQuantite_Article(0);
			articleRepository.save(Jolly);

			Article Lukus =new Article();
			Lukus.setIngredient(ingredient1);
			Lukus.setNature(nature);
			Lukus.setQualite(qualite2);
			Lukus.setMarque(marque);
			Lukus.setDesigniation("HO LUKUS SAR M MarCl");
			Lukus.setQuantite_Article(0);
			articleRepository.save(Lukus);

			Article Lukuss =new Article();
			Lukuss.setIngredient(ingredient2);
			Lukuss.setNature(nature1);
			Lukuss.setQualite(qualite2);
			Lukuss.setMarque(marque1);
			Lukuss.setDesigniation("ST LUKUS THON M MarCl");
			Lukuss.setQuantite_Article(0);
			articleRepository.save(Lukuss);
			
//DEPOTS
			Depot depotEmballage =new Depot();
			depotEmballage.setNom_Depot("Depot Emballage");
			depotEmballage.setQauntiteMax(100000);
			depotEmballage.setNbrMaxAllee(11);
			depotEmballage.setNbrMaxNiveau(5);
			depotEmballage.setNbrMaxRangee(20);
			depotEmballage.setQuantiteActuelle(0);
			depotRepository.save(depotEmballage);


			Depot depotPrFini =new Depot();
			depotPrFini.setNom_Depot("Depot Produit Fini");
			depotPrFini.setQauntiteMax(100000);
			depotPrFini.setNbrMaxAllee(11);
			depotPrFini.setNbrMaxNiveau(5);
			depotPrFini.setNbrMaxRangee(20);
			depotPrFini.setQuantiteActuelle(0);
			depotRepository.save(depotPrFini);

//LES OPERATIONS
			Operation operation=new Operation();
			operation.setQuantite(5666);
			operation.setDepot(depotEmballage);
			operation.setArticle(Jolly);
			operation.setTypeOpr(TypeOp.E);
			operation.setRangee(1);
			operation.setNiveau(2);
			operation.setAllee(1);
			Jolly.setQuantite_Article(Jolly.getQuantite_Article()+operation.getQuantite());
			operation.setPersonne(personneRepository.getReferenceById(1));
			depotEmballage.setQuantiteActuelle(depotEmballage.getQuantiteActuelle()+operation.getQuantite());
			articleRepository.save(Jolly);
			operationRepository.save(operation);
			depotRepository.save(depotEmballage);

			Operation operation2=new Operation();
			operation2.setQuantite(3000);
			operation2.setDepot(depotEmballage);
			operation2.setArticle(Jolly);
			operation2.setTypeOpr(TypeOp.S);
			operation2.setRangee(1);
			operation2.setNiveau(2);
			operation2.setAllee(1);
			depotEmballage.setQuantiteActuelle(depotEmballage.getQuantiteActuelle()-operation2.getQuantite());
			operation2.setPersonne(personneRepository.getReferenceById(1));
			Jolly.setQuantite_Article(Jolly.getQuantite_Article()-operation2.getQuantite());
			articleRepository.save(Jolly);
			operationRepository.save(operation2);
			depotRepository.save(depotEmballage);
			
			Operation operation3=new Operation();
			operation3.setQuantite(2000);
			operation3.setDepot(depotPrFini);
			operation3.setArticle(Lukuss);
			operation3.setTypeOpr(TypeOp.E);
			operation3.setRangee(1);
			operation3.setNiveau(3);
			operation3.setAllee(1);
			depotPrFini.setQuantiteActuelle(depotPrFini.getQuantiteActuelle()+operation3.getQuantite());
			operation3.setPersonne(personneRepository.getReferenceById(1));
			Lukuss.setQuantite_Article(Lukus.getQuantite_Article()+operation3.getQuantite());
			articleRepository.save(Lukuss);
			operationRepository.save(operation3);
			depotRepository.save(depotPrFini);
			
			Operation operation4=new Operation();
			operation4.setQuantite(2000);
			operation4.setDepot(depotPrFini);
			operation4.setArticle(Lukuss);
			operation4.setTypeOpr(TypeOp.E);
			operation4.setRangee(17);
			operation4.setNiveau(4);
			operation4.setAllee(11);
			depotPrFini.setQuantiteActuelle(depotPrFini.getQuantiteActuelle()+operation4.getQuantite());
			Lukuss.setQuantite_Article(Lukuss.getQuantite_Article()+operation4.getQuantite());
			operation4.setPersonne(personneRepository.getReferenceById(2));
			articleRepository.save(Lukuss);
			operationRepository.save(operation4);
			depotRepository.save(depotPrFini);


		};

	} }
