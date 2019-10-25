package com.articles.articlesbackendapp.controllers;

import com.articles.articlesbackendapp.entity.Author;
import com.articles.articlesbackendapp.repos.AuthorRepo;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorRepo authorRepo;

    @GetMapping
    public List<Author> read() {
        return IterableUtils.toList(authorRepo.findAll());
    }

    @PostMapping
    public List<Author> create(@RequestParam String firstName, @RequestParam String lastName ) {
        Author author = new Author(firstName, lastName);
        authorRepo.save(author);
        return read();
    }
}
