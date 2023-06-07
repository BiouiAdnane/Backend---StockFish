package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Block;
import com.example.aveiro_project.Entities.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlockRepo extends JpaRepository<Block,Integer> {
    Block findBlockByAlleeAndRangeeAndNiveauAndDepot(int allee, int rangee, int niveau, Depot depot);
    Block findByAlleeAndRangeeAndNiveauAndDepot(int allee, int rangee, int niveau,Depot depot);
    @Query("SELECT COUNT(b.id_Block) FROM Block b WHERE b.depot.code_Depot = :code_Depot")
    int countByIdBlock(@Param("code_Depot") int code_Depot);
}
