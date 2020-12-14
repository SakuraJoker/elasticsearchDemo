package com.example.demo.service;

import com.example.demo.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Article add(Article article);

    Optional<Article> findById(Article article);

    Iterable<Article> addList(List<Article> list);

    void  deleteById(String id);

    void  deleteInList(List<Article> list);

    Iterable<Article> getList();


    Iterable<Article> getListInIds(List<String> list);

    Page<Article> getPageList(Pageable pageable);

    List<Article> findByTitleCustom(String keyword);

    Page findByTitleCustom(String keyword, Pageable pageable);

    Page<Article> findByKeyword(String title,String content, Pageable pageable);

    Page<Article> findAllByTitleAndContent(String title,String content, Pageable pageable);


    Page queryPageList(Article article, Pageable pageable);

    //添加索引
    boolean  createIndex(Class<?> clazz);
    //删除索引
    boolean  deleteIndex(Class<?> clazz);




}
