package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Marque;
import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Exceptions.PersonneNotFoundException;
import com.example.aveiro_project.Repository.MarqueRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class MarqueServiceImpl implements MarqueService {
    private MarqueRepo marqueRepo;
    @Override
    public List<Marque> listerMarques() {
        return marqueRepo.findAll();
    }

    @Override
    public Marque saveMarque(Marque marque) {
        return marqueRepo.save(marque) ;
    }

    @Override
    public Marque updateMarque(Marque marque) {
        return marqueRepo.save(marque);
    }

    @Override
    public void deleteMarque(int id) {
        marqueRepo.deleteById(id);
    }

    @Override
    public List<Marque> searchMarque(String keyword) {
        List<Marque> marques = marqueRepo.searchMarque(keyword) ;
        return marques;
    }

    @Override
    public Marque getMarqueId(int id_Famille) {
        Marque marque = marqueRepo.findById(id_Famille).orElse(null);
        return marque;
    }
}
