package com.articles.articlesbackendapp.service;

import com.articles.articlesbackendapp.entity.Article;
import com.articles.articlesbackendapp.exceptions.InternalServerErrorException;
import com.articles.articlesbackendapp.repos.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    ArticleRepo articleRepo;

    public Article findById(String id) {
        Article article = articleRepo
            .findById(Long.valueOf(id))
            .orElseThrow(() -> new InternalServerErrorException("Article not found with Id " + id));
        return article;
    }

}
