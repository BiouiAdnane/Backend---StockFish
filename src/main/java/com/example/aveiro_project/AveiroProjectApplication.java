package com.example.aveiro_project;

import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Enums.*;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Repository.ArticleRepository;
import com.example.aveiro_project.Repository.DepotRepository;
import com.example.aveiro_project.Repository.OperationRepository;
import com.example.aveiro_project.Repository.PersonneRepository;
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
							ArticleRepository articleRepository){
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

//LES ARTICLES

			Article Joly =new Article();
			Joly.setIngredient(Ingredient.Huile_Olive);
			Joly.setNature(Nature.Maquereaus);
			Joly.setQualite(Qualite.Bon_Marchand);
			Joly.setMarque(Marque.Propre);
			Joly.setDesigniation("HO JOLLY MAQ BM MarPr");
			articleRepository.save(Joly);

			Article Jolly =new Article();
			Jolly.setIngredient(Ingredient.Huile_Vigitale);
			Jolly.setNature(Nature.Maquereaus);
			Jolly.setQualite(Qualite.Bon_Marchand);
			Jolly.setMarque(Marque.Propre);
			Jolly.setDesigniation("HV JOLLY MAQ BM MarPr");
			articleRepository.save(Jolly);

			Article Lukus =new Article();
			Lukus.setIngredient(Ingredient.Huile_Olive);
			Lukus.setNature(Nature.Sardine);
			Lukus.setQualite(Qualite.Marchand);
			Lukus.setMarque(Marque.Client);
			Lukus.setDesigniation("HO LUKUS SAR M MarCl");
			articleRepository.save(Lukus);

			Article Lukuss =new Article();
			Lukuss.setIngredient(Ingredient.Sauce_Tomate);
			Lukuss.setNature(Nature.Thon);
			Lukuss.setQualite(Qualite.Marchand);
			Lukuss.setMarque(Marque.Client);
			Lukuss.setDesigniation("ST LUKUS THON M MarCl");
			articleRepository.save(Lukuss);

			Article couvercle1 = new Article();
			couvercle1.setDesigniation("CC1 1/4");
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
			operation4.setPersonne(personneRepository.getReferenceById(2));
			operationRepository.save(operation4);
			depotRepository.save(depotPrFini);


		};

	} }
