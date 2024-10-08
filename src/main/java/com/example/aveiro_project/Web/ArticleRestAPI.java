package com.example.aveiro_project.Web;

import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Exceptions.ArticleNotFoundException;
import com.example.aveiro_project.Services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ArticleRestAPI {
    private ArticleService articleService;
    @GetMapping("/articles")
    public List<Article> articles(){
        return articleService.getListArticle();
    }
    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable int id) throws ArticleNotFoundException{
        return articleService.findArticle(id);
    }
    @PostMapping("/articles")
    public Article saveArticle(@RequestBody Article article){
        return articleService.saveArticle(article);
    }
    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable int id,@RequestBody Article article) throws ArticleNotFoundException {
        article.setCode_Article(id);
        return articleService.updateArticle(article);
    }
    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable int id){
        articleService.deleteArticle(id);
    }


    @GetMapping("/articles/search")
    public List<Article> searchArticle(@RequestParam(name = "keyword" , defaultValue = "") String keyword){
        return articleService.searchArticle("%"+keyword+"%");
    }
    @GetMapping("/articles/count")
    public int countArticle(){
        return articleService.countArticle();
    }
}
