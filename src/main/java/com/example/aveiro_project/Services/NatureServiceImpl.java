package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Nature;
import com.example.aveiro_project.Repository.NatureRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class NatureServiceImpl implements NatureService {
    private NatureRepo natureRepo;
    @Override
    public List<Nature> listerNatures() {
        return natureRepo.findAll() ;
    }

    @Override
    public Nature saveNature(Nature nature) {
        return natureRepo.save(nature);
    }

    @Override
    public Nature updateNature(Nature nature) {
        return natureRepo.save(nature);
    }

    @Override
    public void deleteNature(int id) {
        natureRepo.deleteById(id);
    }
}
