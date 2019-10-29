package com.articles.articlesbackendapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.*;

import com.articles.articlesbackendapp.controllers.ArticleController;
import com.articles.articlesbackendapp.entity.Article;
import com.articles.articlesbackendapp.entity.Author;
import com.articles.articlesbackendapp.exceptions.InternalServerErrorException;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import javax.validation.constraints.AssertTrue;

@SpringBootTest
@WebAppConfiguration
public class ArticleControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext; //ArticleController articleController;

    @InjectMocks
    private ArticleController articleController = new ArticleController();

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Ignore
    @Test (expectedExceptions = InternalServerErrorException.class)
    public void testCreateFailed() throws Exception {
        mockMvc.perform(post("/article?title=&summary=summary&text=text&authorId=1"));
    }

    @Test
    public void testRead() throws Exception {
        mockMvc.perform(get( "/article")).andExpect(status().isOk());
    }

    @Test (expectedExceptions = InternalServerErrorException.class)
    public void testCreate_failed_EmptyTitle() {
        articleController.create("", "summary", "text", "1");
    }

    @Test (expectedExceptions = InternalServerErrorException.class)
    public void testCreate_failed_EmptyText() {
        articleController.create("title", "summary", "", "1");
    }

    @Test (expectedExceptions = InternalServerErrorException.class)
    public void testCreate_failed_EmptyAuthor() {
        articleController.create("title", "summary", "text", "");
    }
}