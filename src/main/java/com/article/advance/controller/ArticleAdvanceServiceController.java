package com.article.advance.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.article.advance.jpa.repository.ArticleRepository;
import com.article.advance.model.Article;

@RestController
@RequestMapping("/api")
public class ArticleAdvanceServiceController {
	
	 @Autowired
	 ArticleRepository articleRepository;
	
	
	
	// POST API to create an article with the title, article content and author name.
	@PostMapping("/article")
	  public ResponseEntity<Article> createArticle(@RequestBody Article article) {
	    try {
	      Article articleNew = articleRepository
	          .save(new Article(article.getTitle(), article.getArticleContent(), article.getArticleName()));      return new ResponseEntity<>(articleNew, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  } 
	
	//GET API to list the articles based on Article Title

	@GetMapping("/articles")
	public ResponseEntity<List<Article>> getAllArticles(@RequestParam(required = false) String title) {
		    try {
		        List<Article> articles = new ArrayList<Article>();

		        if (title == null)
		        	articleRepository.findAll().forEach(articles::add);
		        else
		        	articleRepository.findByTitleContaining(title).forEach(articles::add);

		        if (articles.isEmpty()) {
		          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		        }

		        return new ResponseEntity<>(articles, HttpStatus.OK);
		      } catch (Exception e) {
		        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		      }
		    }
	
	// PUT API to update the articles based on ID in the Article Object

	@PutMapping("/articles{id}")
	public ResponseEntity<Article> updateArticleBasedOnId(@PathVariable("id") long id, @RequestBody Article tutorial) {
	    Optional<Article> articleData = articleRepository.findById(id);

	    if (articleData.isPresent()) {
	    	Article updatedArticle = articleData.get();
	    	updatedArticle.setTitle(tutorial.getTitle());
	    	updatedArticle.setArticleContent(tutorial.getArticleContent());
	    	updatedArticle.setArticleName(tutorial.getArticleName());
	      return new ResponseEntity<>(articleRepository.save(updatedArticle), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
}
