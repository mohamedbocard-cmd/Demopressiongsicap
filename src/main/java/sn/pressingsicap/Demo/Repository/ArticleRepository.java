package sn.pressingsicap.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import sn.pressingsicap.Demo.Models.Article;
@Repository
@EnableJpaRepositories
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
