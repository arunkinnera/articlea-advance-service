package com.article.advance.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.article.advance.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	  List<Article> findByTitleContaining(String title);

}
