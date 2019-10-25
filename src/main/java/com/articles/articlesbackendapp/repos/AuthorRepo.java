package com.articles.articlesbackendapp.repos;

import com.articles.articlesbackendapp.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepo extends CrudRepository<Author, Long> {
}
