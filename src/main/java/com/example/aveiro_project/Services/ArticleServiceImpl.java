package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Exceptions.ArticleNotFoundException;
import com.example.aveiro_project.Repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService{
    private ArticleRepository articleRepository;
    @Override
    public Article saveArticle(Article article){
        Article savedArticle = articleRepository.save(article);
        return savedArticle;
    }

    @Override
    public List<Article> getListArticle() {
        List<Article> articles=articleRepository.findAll();
        return articles;
    }

    @Override
    public Article findArticle(int id) throws ArticleNotFoundException {
        Article article=articleRepository.findById(id).orElseThrow( ()->new ArticleNotFoundException("Article not found") ) ;
        return article;
    }

    @Override
    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }

   @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }
}
