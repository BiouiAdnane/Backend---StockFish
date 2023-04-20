package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Marque;

import java.util.List;

public interface MarqueService {
    public List<Marque> listerMarques();
    public Marque saveMarque(Marque marque);
    public Marque updateMarque(Marque marque);
    public void deleteMarque(int id);
}
