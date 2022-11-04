package iagopm.articlesapi.controllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import iagopm.articlesapi.controller.ArticlesController;
import iagopm.articlesapi.persistence.ArticleRepository;
import iagopm.articlesapi.service.ArticlesService;
@TestPropertySource(properties = "spring.cache.type=none")
@WebMvcTest(value = ArticlesController.class)
class ArticlesSaveTests {
	@MockBean
	private ArticlesService service;
	@MockBean
	private ArticleRepository repository;
	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		
	}
	
	@Test
	void saveArticle() throws Exception {
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("name", "Zapatillas ultimo modelo");
		paramsMap.add("description", "Tienen suela de goma");
		paramsMap.add("rating", "2");
		paramsMap.add("price", "99.99");
		paramsMap.add("brand", "puma");
		paramsMap.add("productType", "shoes");

		String expectedResponse = "{\"id\":0,"
				+ "\"name\":\"Zapatillas ultimo modelo\","
				+ "\"description\":\"Tienen suela de goma\","
				+ "\"rating\":2,"
				+ "\"price\":99.99,"
				+ "\"brand\":\"PUMA\","
				+ "\"productType\":\"SHOES\"}";
		
		String response = mvc.perform(post("/api/saveArticle")
				.params(paramsMap)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andDo(print())
		.andReturn().getResponse().getContentAsString();
		assertEquals(expectedResponse,response);
	}
	
	@Test
	void saveArticleWrongproductType() throws Exception {
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("name", "Zapatillas ultimo modelo");
		paramsMap.add("description", "Tienen suela de goma");
		paramsMap.add("rating", "2");
		paramsMap.add("price", "99.99");
		paramsMap.add("brand", "ASICS");
		paramsMap.add("productType", "ZAPATOSSS");

		String expectedResponse = "ProductType is not a valid value[SHOES, TSHIRTS, HOODIES, PANTS, TRACKPANTS]";
		
		String response = mvc.perform(post("/api/saveArticle")
				.params(paramsMap)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest()).andDo(print())
		.andReturn().getResponse().getContentAsString();
		assertEquals(expectedResponse,response);
	}
	@Test
	void saveArticleWrongBrand() throws Exception {
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("name", "Zapatillas ultimo modelo");
		paramsMap.add("description", "Tienen suela de goma");
		paramsMap.add("rating", "2");
		paramsMap.add("price", "99.99");
		paramsMap.add("brand", "asos");
		paramsMap.add("productType", "shoes");

		String expectedResponse = "Brand is not a valid value[NIKE, ADIDAS, ASICS, PUMA, CHAMPION, FILA]";
		
		String response = mvc.perform(post("/api/saveArticle")
				.params(paramsMap)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest()).andDo(print())
		.andReturn().getResponse().getContentAsString();
		assertEquals(expectedResponse,response);
	}
	
	@Test
	void saveArticleWrongPrice() throws Exception {
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("name", "Zapatillas ultimo modelo");
		paramsMap.add("description", "Tienen suela de goma");
		paramsMap.add("rating", "2");
		paramsMap.add("price", "-200");
		paramsMap.add("brand", "ASICS");
		paramsMap.add("productType", "shoes");

		String expectedResponse = "Price  cannot be below 0";
		
		String response = mvc.perform(post("/api/saveArticle")
				.params(paramsMap)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest()).andDo(print())
		.andReturn().getResponse().getContentAsString();
		assertEquals(expectedResponse,response);
	}
	
	@Test
	void saveArticleWrongRating() throws Exception {
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("name", "Zapatillas ultimo modelo");
		paramsMap.add("description", "Tienen suela de goma");
		paramsMap.add("rating", "-1");
		paramsMap.add("price", "99.99");
		paramsMap.add("brand", "puma");
		paramsMap.add("productType", "shoes");

		String expectedResponse = "Rating is not between valid range (0-5)";
		
		String response = mvc.perform(post("/api/saveArticle")
				.params(paramsMap)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest()).andDo(print())
		.andReturn().getResponse().getContentAsString();
		assertEquals(expectedResponse,response);
	}
	
	@Test
	void saveArticleWrongDescription() throws Exception {
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("name", "Zapatillas ultimo modelo");
		paramsMap.add("description", "");
		paramsMap.add("rating", "2");
		paramsMap.add("price", "99.99");
		paramsMap.add("brand", "puma");
		paramsMap.add("productType", "shoes");

		String expectedResponse = "Description cannot be null or empty";
		
		String response = mvc.perform(post("/api/saveArticle")
				.params(paramsMap)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest()).andDo(print())
		.andReturn().getResponse().getContentAsString();
		assertEquals(expectedResponse,response);
	}
	
	@Test
	void saveArticleWrongName() throws Exception {
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("name", "");
		paramsMap.add("description", "Tienen suela de goma");
		paramsMap.add("rating", "2");
		paramsMap.add("price", "99.99");
		paramsMap.add("brand", "puma");
		paramsMap.add("productType", "shoes");

		String expectedResponse = "Name cannot be null or empty";
		
		String response = mvc.perform(post("/api/saveArticle")
				.params(paramsMap)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest()).andDo(print())
		.andReturn().getResponse().getContentAsString();
		assertEquals(expectedResponse,response);
	}
}