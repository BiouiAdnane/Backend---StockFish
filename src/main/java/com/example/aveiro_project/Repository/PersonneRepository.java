package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Personne;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
    @Query("select p from Personne p where p.prenom like :kw")
    List<Personne> searchPersonne(@Param(value = "kw") String keyword);
//    List<Personne> findPersonnesByPrenomContains(String kw);
}
