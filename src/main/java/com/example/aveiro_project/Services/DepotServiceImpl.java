package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Exceptions.DepotNotFoundException;
import com.example.aveiro_project.Repository.DepotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DepotServiceImpl implements DepotService{
    private DepotRepository depotRepository;
    @Override
    public Depot saveDepot(Depot depot) {
        depot.setQauntiteMax(depot.getNbrMaxAllee()* depot.getNbrMaxRangee()* depot.getNbrMaxNiveau());
        return depotRepository.save(depot);
    }

    @Override
    public List<Depot> depots() {
        return depotRepository.findAll() ;
    }

    @Override
    public Depot findDepot(int id) {
        return depotRepository.findById(id).orElse(null) ;
    }

    @Override
    public void deleteDepot(int id) {
        depotRepository.deleteById(id);
    }

    @Override
    public Depot updateDepot(Depot depot) throws DepotNotFoundException {
        depot.setQauntiteMax(depot.getNbrMaxAllee()* depot.getNbrMaxRangee()* depot.getNbrMaxNiveau());
        return depotRepository.save(depot);
    }

    @Override
    public List<Depot> searchDepot(String keyword) {
        List<Depot> depots =depotRepository.searchDepot(keyword);
        return depots;
    }

}
