package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Block;
import com.example.aveiro_project.Entities.Depot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepo extends JpaRepository<Block,Integer> {
    Block findBlockByAlleeAndRangeeAndNiveauAndDepot(int allee, int rangee, int niveau, Depot depot);
    Block findByAlleeAndRangeeAndNiveauAndDepot(int allee, int rangee, int niveau,Depot depot);
}
