package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.*;
import com.example.aveiro_project.Repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional @AllArgsConstructor
public class FamilleServiceImpl implements FamilleService {
    private FamilleRepo familleRepo;
    @Override
    public List<Famille> listerFamilles() {
        return familleRepo.findAll() ;
    }

    @Override
    public Famille getFamille(int id) {
        return familleRepo.findById(id).orElse(null) ;
    }

    @Override
    public Famille updateFamille(Famille famille) {
        return familleRepo.save(famille);
    }

    @Override
    public List<Famille> findByNom(String kw) {
        return familleRepo.findFamilleByNomContaining(kw);
    }


}
