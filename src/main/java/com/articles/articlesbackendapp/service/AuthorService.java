package com.articles.articlesbackendapp.service;

import com.articles.articlesbackendapp.entity.Author;
import com.articles.articlesbackendapp.exceptions.InternalServerErrorException;
import com.articles.articlesbackendapp.repos.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public Author findById(String id) {
        Author author = authorRepo
            .findById(Long.valueOf(id))
            .orElseThrow(() -> new InternalServerErrorException("Author not found with Id " + id));
        return author;
    }
}
