package iagopm.articlesapi.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import iagopm.articlesapi.persistence.ArticleRepository;
import iagopm.articlesapi.service.ArticlesService;
@TestPropertySource(properties = "spring.cache.type=none")
@SpringBootTest
@AutoConfigureMockMvc
class ArticleDeleteTests {

		@MockBean
		private ArticlesService service;
		@MockBean
		private ArticleRepository repository;
		@Autowired
		private MockMvc mvc;

		@BeforeTestExecution
		void generateArticles() throws UnsupportedEncodingException, Exception {
			MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
			paramsMap.add("name", "Zapatillas ultimo modelo");
			paramsMap.add("description", "Tienen suela de goma");
			paramsMap.add("rating", "2");
			paramsMap.add("price", "99.99");
			paramsMap.add("brand", "puma");
			paramsMap.add("productType", "shoes");

			
			mvc.perform(post("/api/saveArticle")
					.params(paramsMap)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andDo(print())
			.andReturn().getResponse().getContentAsString();
		}

		@Test
		void deleteArticle() throws Exception {
			MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
			paramsMap.add("id", "0");
			

			String expectedResponse = "Deleted successfully";
			
			String response = mvc.perform(delete("/api/deleteArticleById")
					.params(paramsMap)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andDo(print())
			.andReturn().getResponse().getContentAsString();
			assertEquals(expectedResponse,response);
		}

		@AfterTestExecution
		void checkIsDeleted() throws UnsupportedEncodingException, Exception {
			MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
			paramsMap.add("id", "0");
			

			String expectedResponse = "{\"id\":0,"
					+ "\"name\":\"Zapatillas ultimo modelo\","
					+ "\"description\":\"Tienen suela de goma\","
					+ "\"rating\":2,"
					+ "\"price\":99.99,"
					+ "\"brand\":\"PUMA\","
					+ "\"productType\":\"SHOES\"}";
			
			String response = mvc.perform(get("/api/findArticleById")
					.params(paramsMap)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andDo(print())
			.andReturn().getResponse().getContentAsString();
			assertNotEquals(expectedResponse,response);
		}
}