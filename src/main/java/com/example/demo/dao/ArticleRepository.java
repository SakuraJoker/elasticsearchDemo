package com.example.demo.dao;

import com.example.demo.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    /**
     * query注解创建查询
     * @param keyword
     * @return
     */
    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
   // @Query("{\"bool\": {\"must\": { \"match\": { \"title\": \"?0\"}}}}")
    List<Article> findByTitleCustom(String keyword);

    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    Page<Article> findByTitleCustom(String keyword, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{ \"match\": { \"title\": \"?0\"}},{ \"match\": { \"content\": \"?1\"}}]}}")
    Page<Article> findByKeyword(String title,String content, Pageable pageable);
    /**
     * 根据标题，内容两个字段搜索
     * */
    Page<Article> findAllByTitleAndContent(String title,String content, Pageable pageable);
}
