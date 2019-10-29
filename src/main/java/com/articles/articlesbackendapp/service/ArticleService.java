package com.articles.articlesbackendapp.service;

import com.articles.articlesbackendapp.entity.Article;
import com.articles.articlesbackendapp.exceptions.InternalServerErrorException;
import com.articles.articlesbackendapp.repos.ArticleRepo;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepo articleRepo;

    public List<Article> findAll() {
        return articleRepo.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    public Article findById(String id) {
        Article article = articleRepo
            .findById(Long.valueOf(id))
            .orElseThrow(() -> new InternalServerErrorException("Article not found with Id " + id));
        return article;
    }

}
