package com.example.aveiro_project;

import com.example.aveiro_project.Entities.*;
import com.example.aveiro_project.Enums.*;
import com.example.aveiro_project.Repository.*;
import com.example.aveiro_project.Services.PaletteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Stream;
@Slf4j
@SpringBootApplication
public class AveiroProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AveiroProjectApplication.class, args);
	}
//	@Bean
	CommandLineRunner start(PersonneRepository personneRepository,
							DepotRepository depotRepository,
							OperationRepository operationRepository,
							ArticleRepository articleRepository,
							FamilleRepo familleRepo,
							BlockRepo blockRepo,
							PaletteService paletteService){
		return args -> {
			Stream.of("Adnan", "Ahmed", "Mourad").forEach(name->{
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
				personneRepository.save(personne);

			});

			// FAMILLES
			Ingredient ingredient1 =new Ingredient() ;
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

			Article Joly =Article.builder()
					.size(SizeArticle.Small)
					.typeArticle(TypeArticle.Produit_Fini)
					.ingredient(ingredient1)
					.nature(nature1)
					.qualite(qualite1)
					.marque(marque1)
					.designiation("HO JOLLY MAQ BM MarPr")
					.quantite_Article(0)
					.size(SizeArticle.Small)
					.typeArticle(TypeArticle.Carton)
					.build();

			articleRepository.save(Joly);

			Article Jolly =Article.builder()
					.size(SizeArticle.Big)
					.typeArticle(TypeArticle.Couverle)
					.ingredient(ingredient2)
					.nature(nature)
					.qualite(qualite)
					.marque(marque)
					.designiation("HV JOLLY MAQ BM MarPr")
					.quantite_Article(0)
					.size(SizeArticle.Medium)
					.typeArticle(TypeArticle.Etui)
					.build();

			articleRepository.save(Jolly);

			Article Lukus =Article.builder()
					.size(SizeArticle.Medium)
					.typeArticle(TypeArticle.Produit_Fini)
					.ingredient(ingredient1)
					.nature(nature)
					.qualite(qualite2)
					.marque(marque)
					.designiation("HO LUKUS SAR M MarCl")
					.quantite_Article(0)
					.size(SizeArticle.Big)
					.typeArticle(TypeArticle.Produit_Fini)
					.build();

			articleRepository.save(Lukus);

			Article Lukuss =Article.builder()
					.size(SizeArticle.Small)
					.typeArticle(TypeArticle.Etui)
					.ingredient(ingredient2)
					.nature(nature1)
					.qualite(qualite2)
					.marque(marque1)
					.designiation("ST LUKUS THON M MarCl")
					.quantite_Article(0)
					.size(SizeArticle.Small)
					.typeArticle(TypeArticle.Produit_Fini)
					.build();

			articleRepository.save(Lukuss);
			
//DEPOTS
			Depot depotEmballage = Depot.builder()
					.nom_Depot("Depot Emballage")
					.qauntiteMax(1100)
					.nbrMaxAllee(11)
					.nbrMaxNiveau(5)
					.nbrMaxRangee(20)
					.quantiteActuelle(0)
					.build();

			depotRepository.save(depotEmballage);


			Depot depotPrFini = Depot.builder()
					.nom_Depot("Depot Produit Fini")
					.qauntiteMax(1100)
					.nbrMaxAllee(11)
					.nbrMaxNiveau(5)
					.nbrMaxRangee(20)
					.quantiteActuelle(0)
					.build();

			depotRepository.save(depotPrFini);

//LES OPERATIONS
			/*Operation operation=new Operation();
			Block block=new Block();
			operation.setQuantite(5666);
			operation.setDepot(depotEmballage);
			operation.setArticle(Jolly);
			operation.setTypeOpr(TypeOp.E);
			operation.setAllee(1);
			operation.setRangee(1);
			operation.setNiveau(2);
			block.setAllee(operation.getAllee());
			block.setRangee(operation.getRangee());
			block.setNiveau(operation.getNiveau());
			block.setDepot(operation.getDepot());
			block.setQuantite(operation.getQuantite());
			log.info(block.toString());
			operation.setN_Lot("ET"+ getNumberOfDaysSinceStartOfYear(LocalDate.now())+"C");
			operation.setDateOpertaion(new Date());
			Jolly.setQuantite_Article(Jolly.getQuantite_Article()+operation.getQuantite());
			operation.setPersonne(personneRepository.getReferenceById(1));
			depotEmballage.setQuantiteActuelle(depotEmballage.getQuantiteActuelle()+operation.getQuantite());
			depotEmballage.setQuantiteActuelle(blockRepo.countByIdBlock(depotEmballage.getCode_Depot()));
			articleRepository.save(Jolly);
			blockRepo.save(block);
			operationRepository.save(operation);
			depotRepository.save(depotEmballage);

			Operation operation2=new Operation();
			operation2.setQuantite(3000);
			operation2.setDepot(depotEmballage);
			operation2.setArticle(Jolly);
			operation2.setTypeOpr(TypeOp.S);
			operation2.setAllee(1);
			operation2.setRangee(1);
			operation2.setNiveau(2);
			operation2.setDateOpertaion(new Date());
			Block block2=blockRepo.findBlockByAlleeAndRangeeAndNiveauAndDepot(operation2.getAllee(),operation2.getRangee(),operation2.getNiveau(),operation2.getDepot());
			log.info(block.toString());
			operation2.setN_Lot("ET"+ getNumberOfDaysSinceStartOfYear(LocalDate.now())+"C");
			depotEmballage.setQuantiteActuelle(depotEmballage.getQuantiteActuelle()-operation2.getQuantite());
			operation2.setPersonne(personneRepository.getReferenceById(1));
			Jolly.setQuantite_Article(Jolly.getQuantite_Article()-operation2.getQuantite());
			depotEmballage.setQuantiteActuelle(blockRepo.countByIdBlock(depotEmballage.getCode_Depot()));
			blockRepo.save(block2);
			articleRepository.save(Jolly);
			operationRepository.save(operation2);
			depotRepository.save(depotEmballage);
			
			Operation operation3=new Operation();
			Block block3=new Block();
			operation3.setQuantite(2000);
			operation3.setDepot(depotPrFini);
			operation3.setArticle(Lukuss);
			operation3.setTypeOpr(TypeOp.E);
			operation3.setRangee(1);
			operation3.setNiveau(3);
			operation3.setAllee(1);
			block3.setAllee(operation3.getAllee());
			block3.setRangee(operation3.getRangee());
			block3.setNiveau(operation3.getNiveau());
			block3.setDepot(operation3.getDepot());
			block3.setQuantite(operation3.getQuantite());
			operation3.setDateOpertaion(new Date());
			operation3.setN_Lot("ET"+ getNumberOfDaysSinceStartOfYear(LocalDate.now())+"C");
			depotPrFini.setQuantiteActuelle(depotPrFini.getQuantiteActuelle()+operation3.getQuantite());
			operation3.setPersonne(personneRepository.getReferenceById(1));
			Lukuss.setQuantite_Article(Lukus.getQuantite_Article()+operation3.getQuantite());
			depotPrFini.setQuantiteActuelle(blockRepo.countByIdBlock(depotPrFini.getCode_Depot()));
			articleRepository.save(Lukuss);
			operationRepository.save(operation3);
			blockRepo.save(block3);
			depotRepository.save(depotPrFini);
			
			Operation operation4=new Operation();
			Block block4=new Block();
			operation4.setQuantite(2000);
			operation4.setDepot(depotPrFini);
			operation4.setArticle(Lukuss);
			operation4.setTypeOpr(TypeOp.E);
			operation4.setRangee(17);
			operation4.setNiveau(4);
			operation4.setAllee(11);
			block4.setAllee(operation4.getAllee());
			block4.setRangee(operation4.getRangee());
			block4.setNiveau(operation4.getNiveau());
			block4.setDepot(operation4.getDepot());
			block4.setQuantite(operation4.getQuantite());
			operation4.setDateOpertaion(new Date());
			operation4.setN_Lot("ET"+ getNumberOfDaysSinceStartOfYear(LocalDate.now())+"C");
			depotPrFini.setQuantiteActuelle(depotPrFini.getQuantiteActuelle()+operation4.getQuantite());
			Lukuss.setQuantite_Article(Lukuss.getQuantite_Article()+operation4.getQuantite());
			operation4.setPersonne(personneRepository.getReferenceById(2));
			depotPrFini.setQuantiteActuelle(blockRepo.countByIdBlock(depotPrFini.getCode_Depot()));
			blockRepo.delete(block4);
			articleRepository.save(Lukuss);
			operationRepository.save(operation4);
			depotRepository.save(depotPrFini);*/



			//PALETTES

			Palette palette_Big =Palette.builder()
					.codePalette(1)
					.sizeArticle(SizeArticle.Big)
					.quantiteMax(3000)
					.build();

			Palette palette_Medium= Palette.builder()
					.codePalette(2)
					.sizeArticle(SizeArticle.Medium)
					.quantiteMax(4000)
					.build();

			Palette palette_Small=Palette.builder()
					.codePalette(3)
					.sizeArticle(SizeArticle.Small)
					.quantiteMax(5000)
					.build();

			paletteService.updatePalette(palette_Small);
			paletteService.updatePalette(palette_Big);
			paletteService.updatePalette(palette_Medium);
		};

	}
	public static long getNumberOfDaysSinceStartOfYear(LocalDate date) {

		LocalDate startOfYear = LocalDate.of(date.getYear(), 1, 1);


		return date.toEpochDay() - startOfYear.toEpochDay()+1;
	}
}
