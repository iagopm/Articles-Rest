package iagopm.articlesapi.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import iagopm.articlesapi.exceptions.ArticleException;
import iagopm.articlesapi.model.Article;
import iagopm.articlesapi.persistence.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticlesService {
	private static Logger logger = LogManager.getLogger(ArticleServiceImpl.class);

	@Autowired
	private ArticleRepository repository;

	@Override
	public void saveArticle(Article article) {
		repository.save(article);
		logger.info("ARTICLE PERSISTED {}", article);
	}

	@Cacheable("article")
	@Override
	public Article findArticleById(String id) throws ArticleException {
		logger.debug("Retrieving from DB");
		if (id != null) {
			int parsedId = Integer.parseInt(id);
			Optional<Article> article = repository.findById(parsedId);
			if (article.isPresent()) {
				logger.info("ARTICLE WITH ID {} was found", parsedId);
				return article.get();
			} else {
				throw new ArticleException("Article not found");
			}
		} else {
			throw new ArticleException("Article id must'nt be empty");
		}
	}

	@Override
	public void modifyArticleById(Article article) throws ArticleException {
		Article current = findArticleById(""+article.getId());
		if (current != null) {
			saveArticle(article);
			logger.info("ARTICLE MODIFIED {}", article);
		} else {
			throw new ArticleException("Article not found for modification");
		}
	}

	@Override
	public void deleteArticleById(String id) throws ArticleException {
		if (id != null) {
			int parsedId = Integer.parseInt(id);
			repository.deleteById(parsedId);
			logger.info("ARTICLE DELETED WITH ID {}", id);
		} else {
			throw new ArticleException("Article id must'nt be empty");
		}
		logger.info("ARTICLE DELETED WITH ID {}", id);
	}
}