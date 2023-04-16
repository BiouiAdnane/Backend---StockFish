package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.*;

import javax.lang.model.element.Name;
import java.util.List;

public interface FamilleService {
    public Famille saveFamille(Famille famille);
    public List<Famille> listerFamilles();
    public Famille getFamille(int id);
    public Famille updateFamille(Famille famille);
    public void deleteFamille(int id);
    public List<Marque> listerMarques();
    public List<Nature> listerNatures();
    public List<Ingredient> listerIngredients();
    public  List<Qualite> listerQualites();
}
