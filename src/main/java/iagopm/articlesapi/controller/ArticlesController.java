package iagopm.articlesapi.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import iagopm.articlesapi.builders.ArticleBuilder;
import iagopm.articlesapi.model.Article;
import iagopm.articlesapi.service.ArticlesService;

@RestController
public class ArticlesController {
	private static Logger logger = LogManager.getLogger(ArticlesController.class);

	@Autowired
	private ArticlesService articlesService;

	@PutMapping(path = "/api/modifyArticleById")
	@ResponseBody
	public ResponseEntity<Object> modifyArticle(@RequestBody Article article) {
		try {
			articlesService.modifyArticleById(article);
			return ResponseEntity.ok("Modified successfully");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping(path = "/api/deleteArticleById")
	@ResponseBody
	public ResponseEntity<Object> deleteArticle(@RequestParam("id") String id) {
		try {
			articlesService.deleteArticleById(id);
			return ResponseEntity.ok("Deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping(path = "/api/findArticleById", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> saveArticle(@RequestParam("id") String id) {
		Article article = null;
		try {
			// Metodo cacheable
			article = articlesService.findArticleById(id);
			return ResponseEntity.ok(article);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PostMapping(path = "/api/saveArticle", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> saveArticle(@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("rating") String rating,
			@RequestParam("price") String price, @RequestParam("brand") String brand,
			@RequestParam("productType") String productType) {
		Article article = null;
		try {
			article = ArticleBuilder.build(name, description, rating, price, brand, productType);
			articlesService.saveArticle(article);
			return ResponseEntity.ok(article);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}