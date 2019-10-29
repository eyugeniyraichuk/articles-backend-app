package com.articles.articlesbackendapp.repos;

import com.articles.articlesbackendapp.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article, Long> {
}
