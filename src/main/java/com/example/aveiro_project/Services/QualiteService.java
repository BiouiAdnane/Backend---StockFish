package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Ingredient;
import com.example.aveiro_project.Entities.Nature;
import com.example.aveiro_project.Entities.Qualite;

import java.util.List;

public interface QualiteService {
    public List<Qualite> listerQualites();
    Qualite saveQualite(Qualite qualite);
    Qualite updateQualite(Qualite qualite);
    void deleteQualite(int id);

    List<Qualite> searchQualite(String keyword);

}
