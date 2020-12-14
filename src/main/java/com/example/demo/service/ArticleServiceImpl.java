package com.example.demo.service;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.entity.Article;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Article add(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Optional<Article> findById(Article article) {
        return articleRepository.findById(article.getId());
    }

    @Override
    public Iterable<Article> addList(List<Article> list) {
        return articleRepository.saveAll(list);
    }

    @Override
    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void deleteInList(List<Article> list) {
        articleRepository.deleteAll(list);
    }

    @Override
    public Iterable<Article> getList() {
        return articleRepository.findAll();
    }


    @Override
    public Iterable<Article> getListInIds(List<String> list) {
        return articleRepository.findAllById(list);
    }

    @Override
    public Page<Article> getPageList(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public List<Article> findByTitleCustom(String keyword) {
        return articleRepository.findByTitleCustom(keyword);
    }

    @Override
    public Page findByTitleCustom(String keyword,Pageable pageable) {
        return articleRepository.findByTitleCustom(keyword,pageable);
    }

    @Override
    public Page<Article> findByKeyword(String title, String content, Pageable pageable) {
        return articleRepository.findByKeyword(title,content,pageable);
    }

    @Override
    public Page<Article> findAllByTitleAndContent(String title, String content, Pageable pageable) {
        return articleRepository.findAllByTitleAndContent(title,content,pageable);
    }

    @Override
    public Page queryPageList(Article article, Pageable pageable) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (!StringUtils.isEmpty(article.getTitle())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("title", article.getTitle()));
        }

        if (!StringUtils.isEmpty(article.getAbstracts())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("abstracts", article.getAbstracts()));
        }

        if (!StringUtils.isEmpty(article.getContent())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("content", article.getContent()));
        }

        if (!StringUtils.isEmpty(article.getTime())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("time").gt(article.getTime()));
        }
        // BoolQueryBuilder (Spring Query)
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(boolQueryBuilder)
                .build();

        // page search
        Page<Article> articlePage = elasticsearchTemplate.queryForPage(searchQuery, Article.class);

        return articlePage;
    }

    @Override
    public boolean createIndex(Class<?> clazz) {
      return   elasticsearchTemplate.createIndex(clazz);
    }

    @Override
    public boolean deleteIndex(Class<?> clazz) {
        return elasticsearchTemplate.deleteIndex(clazz);

    }


}
