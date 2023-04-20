package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.*;

import java.util.List;

public interface FamilleService {
    public List<Famille> listerFamilles();
    public Famille getFamille(int id);
    public Famille updateFamille(Famille famille);
    public List<Famille> findByNom(String kw);
}
