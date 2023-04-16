package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Exceptions.ArticleNotFoundException;

import java.util.List;

public interface ArticleService {
    public Article saveArticle (Article article)  ;
    public List<Article> getListArticle();
    public Article findArticle(int id) throws ArticleNotFoundException ;
    public void deleteArticle(int id);
    public Article updateArticle(Article article) throws ArticleNotFoundException;
}
