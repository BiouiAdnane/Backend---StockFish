package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Marque;
import com.example.aveiro_project.Entities.Personne;

import java.util.List;

public interface MarqueService {
    public List<Marque> listerMarques();
    public Marque saveMarque(Marque marque);
    public Marque updateMarque(Marque marque);
    public void deleteMarque(int id);
    List<Marque> searchMarque(String keyword);
    Marque getMarqueId(int id_Famille);
}
