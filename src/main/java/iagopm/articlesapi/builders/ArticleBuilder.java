package iagopm.articlesapi.builders;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import iagopm.articlesapi.enums.Brand;
import iagopm.articlesapi.enums.ProductType;
import iagopm.articlesapi.exceptions.ArticleException;
import iagopm.articlesapi.model.Article;

public class ArticleBuilder {
	private static Logger logger = LogManager.getLogger(ArticleBuilder.class);

	private static Brand[] brands = Brand.values();
	private static ProductType[] productTypes = ProductType.values();

	public static Article build(String name, String description, String rating, String price, String brand,
			String productType) throws ArticleException {

		// Se construye el objeto mientras se contemplan posibles errores

		Article article = new Article();
		if (name != null && !name.isEmpty()) {
			article.setName(name);
		} else {
			throw new ArticleException("Name cannot be null or empty");
		}
		if (description != null && !description.isEmpty()) {
			article.setDescription(description);
		} else {
			throw new ArticleException("Description cannot be null or empty");
		}
		if (rating != null) {
			int parsedRating = Integer.parseInt(rating);
			if (parsedRating >= 0 && parsedRating <= 5) {
				article.setRating(parsedRating);
			} else {
				throw new ArticleException("Rating is not between valid range (0-5)");
			}
		} else {
			throw new ArticleException("Rating is not a valid value");
		}
		if (price != null) {
			Double parsedPrice = Double.parseDouble(price);
			if (parsedPrice >= 0) {
				article.setPrice(parsedPrice);
			} else {
				throw new ArticleException("Price  cannot be below 0");
			}
		} else {
			throw new ArticleException("Price  cannot be null or below 0");
		}
		if (brand != null) {
			brand = brand.toUpperCase();
			for (Brand b : brands) {
				if (brand.equals(b.toString())) {
					article.setBrand(b);
				}
			}
			if (article.getBrand() == null) {
				throw new ArticleException("Brand is not a valid value" + Arrays.toString(brands));
			}
		} else {
			throw new ArticleException("Brand cannot be empty");
		}
		if (productType != null) {
			productType = productType.toUpperCase();
			for (ProductType p : productTypes) {
				if (productType.equals(p.toString())) {
					article.setProductType(p);
				}
			}
			if (article.getProductType() == null) {
				throw new ArticleException("ProductType is not a valid value" + Arrays.toString(productTypes));
			}
		} else {
			throw new ArticleException("ProductType cannot be empty");
		}
		logger.info("ARTICLE TO PERSIST {}", article);
		return article;
	}
}