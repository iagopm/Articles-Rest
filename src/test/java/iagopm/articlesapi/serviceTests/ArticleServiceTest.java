package iagopm.articlesapi.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import iagopm.articlesapi.builders.ArticleBuilder;
import iagopm.articlesapi.exceptions.ArticleException;
import iagopm.articlesapi.model.Article;
import iagopm.articlesapi.service.ArticlesService;
@TestPropertySource(properties = "spring.cache.type=none")
@SpringBootTest
class ArticleServiceTest {
	@Autowired
	private ArticlesService service;

	@PostConstruct
	void generateArticle() throws ArticleException {
		Article article = ArticleBuilder.build("asd", "asd", "2","2" , "PUMA", "SHOES");
		article.setId(1);
		service.saveArticle(article);
	}

	@Test
	void saveArticle() throws ArticleException {
		Article saved = service.findArticleById("1");
		assertNotNull(saved);
	};

	@Test
	void modifyArticleById() throws ArticleException {
		Article saved = service.findArticleById("1");
		saved.setName("PAKITO");
		
		service.modifyArticleById(saved);
		
		saved = service.findArticleById("1");
		assertEquals(saved.getName(), "PAKITO");;
	}

	@Test
	void deleteArticleById() throws ArticleException {
		service.deleteArticleById("1");
		try {
			service.findArticleById("1");
		} catch (Exception e) {
			assertInstanceOf(ArticleException.class, e);
		} finally {
			generateArticle();
		}
	}

	@Test
	void findArticleById() throws ArticleException {
		Article saved = service.findArticleById("1");
		assertNotNull(saved);
	}
}