package com.articles.articlesbackendapp.repos;

import com.articles.articlesbackendapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}
