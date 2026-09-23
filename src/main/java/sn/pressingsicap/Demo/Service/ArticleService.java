package sn.pressingsicap.Demo.Service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Article;

import java.util.List;
import java.util.Optional;
@Service
@CacheConfig(cacheNames = "articles")
public interface ArticleService {

    void saveArticle(Article article);

    Optional<Article> getArticleById(Long id);
    List<Article> listArticle();
    void deleteArticleById(Long id);

}
