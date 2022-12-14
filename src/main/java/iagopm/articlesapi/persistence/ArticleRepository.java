package iagopm.articlesapi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iagopm.articlesapi.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}