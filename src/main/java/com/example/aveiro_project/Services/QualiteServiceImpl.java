package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Qualite;
import com.example.aveiro_project.Repository.QualiteRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class QualiteServiceImpl implements QualiteService {
    private QualiteRepo qualiteRepo;
    @Override
    public List<Qualite> listerQualites() {
        return qualiteRepo.findAll() ;
    }

    @Override
    public Qualite saveQualite(Qualite qualite) {
        return qualiteRepo.save(qualite);
    }

    @Override
    public Qualite updateQualite(Qualite qualite) {
        return qualiteRepo.save(qualite);
    }

    @Override
    public void deleteQualite(int id) {
        qualiteRepo.deleteById(id);
    }

}
