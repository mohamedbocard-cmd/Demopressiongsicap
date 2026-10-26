package sn.pressingsicap.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.pressingsicap.Demo.Models.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
