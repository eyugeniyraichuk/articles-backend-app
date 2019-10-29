package com.articles.articlesbackendapp.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.articles.articlesbackendapp.entity.Article;
import com.articles.articlesbackendapp.entity.Author;
import com.articles.articlesbackendapp.exceptions.InternalServerErrorException;
import com.articles.articlesbackendapp.repos.ArticleRepo;
import com.articles.articlesbackendapp.repos.AuthorRepo;
import com.articles.articlesbackendapp.service.ArticleService;
import com.articles.articlesbackendapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AuthorService authorService;

    public ArticleController() {
    }

    @RequestMapping( params = { "title", "summary" , "text", "authorId"}, method = POST)
    @ResponseBody
    public List<Article> create(@RequestParam String title, @RequestParam String summary, @RequestParam String text, @RequestParam String authorId
    ) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(text) || StringUtils.isEmpty(authorId)) {
            throw new InternalServerErrorException("Unable to create Article. One of [title, text, authorId] is required");
        }
        Author author = authorService.findById(authorId);
        Article article = new Article(title, summary, text, author);
        articleRepo.save(article);

        return read();
    }

    @GetMapping
    public List<Article> read() {
        return articleService.findAll();
    }

    @GetMapping("{id}")
    public Article read(@PathVariable String id) {
        return articleService.findById(id);
    }

    @RequestMapping( value = "/{id}", params = { "title", "summary" , "text"}, method = PUT)
    @ResponseBody
    public List<Article> update(@PathVariable String id ,
                         @RequestParam String title, @RequestParam String summary, @RequestParam String text) {
        Article article = articleService.findById(id);

        article.setTitle(title);
        article.setSummary(summary);
        article.setText(text);

        articleRepo.save(article);

        return read();
    }

    @RequestMapping( params = {"id"}, method = DELETE)
    @ResponseBody
    public List<Article> delete(@RequestParam String id) {
        Article article = articleService.findById(id);
        articleRepo.delete(article);
        return read();
    }
}
