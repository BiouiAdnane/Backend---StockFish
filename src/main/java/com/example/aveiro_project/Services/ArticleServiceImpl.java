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
    public Article findArticle(int id) {
        Article article=articleRepository.findById(id).orElse(null);
        return article;
    }

    @Override
    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }


   /* public Article updateArticle(int id, Article article) throws ArticleNotFoundException {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with id: " + id));
        existingArticle.setIngredient(article.getIngredient());
        existingArticle.setNature(article.getNature());
        existingArticle.setMarque(article.getMarque());
        existingArticle.setQualite(article.getQualite());
        existingArticle.setDesigniation(article.getDesigniation());
        return articleRepository.save(existingArticle);
    }*/
   @Override
    public Article updateArticle(Article article) throws ArticleNotFoundException {
        return articleRepository.save(article);
    }
}
