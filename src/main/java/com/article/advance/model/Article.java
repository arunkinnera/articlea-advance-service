package com.article.advance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "articles")
public class Article {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
@Column(name = "title")
private String title;
@Column(name = "articleContent")
private String articleContent;
@Column(name = "articleName")
private String articleName;

public Article() {

}

public Article(String title, String articleContent, String articleName) {
	this.title = title;
	this.articleContent = articleContent;
	this.articleName = articleName;
}
public long getId() {
	return id;
}

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}

public String getArticleContent() {
	return articleContent;
}
public void setArticleContent(String articleContent) {
	this.articleContent = articleContent;
}

public String getArticleName() {
	return articleName;
}
public void setArticleName(String articleName) {
	this.articleName = articleName;
}
}
