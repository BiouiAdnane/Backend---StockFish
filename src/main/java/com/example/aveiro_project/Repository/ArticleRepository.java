package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Enums.SizeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer > {
    @Query("select a from Article a where a.designiation like :kw")
    List<Article> searchArticle(@Param(value = "kw") String keyword);
}
