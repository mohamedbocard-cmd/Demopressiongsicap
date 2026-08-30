package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Article;
import sn.pressingsicap.Demo.Repository.ArticleRepository;
import sn.pressingsicap.Demo.Repository.CommandeRepository;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@CacheConfig(cacheNames = "articles")
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);


    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> listArticle() {
        return articleRepository.findAll();
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);

    }
}
