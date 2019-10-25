package com.articles.articlesbackendapp.repos;

import com.articles.articlesbackendapp.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepo extends CrudRepository<Article, Long> {
}
