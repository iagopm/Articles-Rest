package iagopm.articlesapi.service;

import org.springframework.stereotype.Service;

import iagopm.articlesapi.exceptions.ArticleException;
import iagopm.articlesapi.model.Article;
@Service
public interface ArticlesService {
	public void saveArticle(Article article);

	public void modifyArticleById(Article article) throws ArticleException;

	public void deleteArticleById(String id) throws ArticleException;

	public Article findArticleById(String id) throws ArticleException;
}