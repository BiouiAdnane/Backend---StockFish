package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer > {
}
