package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.entity.base.AjaxResult;
import com.example.demo.entity.base.PageEntity;
import com.example.demo.service.ArticleService;
import com.example.demo.vo.ArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/elasticsearch")
public class ArticleContrller {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public AjaxResult add(@Valid Article article) {
        article.setTime(new Date());
        return AjaxResult.success("操作成功！",articleService.add(article));
    }
    @PostMapping("/addlist")
    public AjaxResult addlist(@Valid Article article) {
        List<Article> list=  new ArrayList<>();
        for (int i=0;i<50;i++){
            Article artic=new Article();
            artic.setTime(new Date());
            artic.setTitle("标题"+i);
            artic.setAbstracts("摘要"+i);
            artic.setContent("内容"+i);
            list.add(artic);
        }
        article.setTime(new Date());
        return AjaxResult.success("操作成功！",articleService.addList(list));
    }

    @PostMapping("/findById")
    public AjaxResult findById(@Valid Article article) {

        return AjaxResult.success("操作成功！",articleService.findById(article));
    }

    @PostMapping("/delete")
    public AjaxResult delete(@Valid Article article) {
        articleService.deleteById(article.getId());
        return AjaxResult.success("操作成功！");
    }

    @PostMapping("/deleteList")
    public AjaxResult deletelist(@Valid Article article) {
        articleService.deleteInList(new ArrayList<>());
        return AjaxResult.success("操作成功！");
    }
    @PostMapping("/list")
    public AjaxResult list(@Valid Article article) {
        return AjaxResult.success("操作成功！",articleService.getList());
    }


    @PostMapping("/getListInIds")
    public AjaxResult getListInIds(@Valid List<String> list) {
        return AjaxResult.success("操作成功！",articleService.getListInIds(list));
    }

    @PostMapping("/queryList")
    public AjaxResult queryList(@Valid Article article) {
        return AjaxResult.success("操作成功！",articleService.findByTitleCustom(article.getTitle()));
    }

    @PostMapping("/getPageList")
    public AjaxResult getPageList(@Valid PageEntity page) {
        Pageable pageable =  PageRequest.of(page.getPageNum(),page.getPageSize());
        return AjaxResult.success("操作成功！",articleService.getPageList(pageable).getContent());
    }

    @PostMapping("/getQueryList")
    public AjaxResult getPageList(@Valid ArticleVo article) {
        Pageable pageable =  PageRequest.of(article.getPageNum(),article.getPageSize());
        Page<Article> articlePage= articleService.findByTitleCustom(article.getTitle(),pageable);
        Page<Article> articles= articleService.findByKeyword(article.getTitle(),article.getContent(),pageable);
        Page<Article> titleAndContent= articleService.findAllByTitleAndContent(article.getTitle(),article.getContent(),pageable);

        return AjaxResult.success("操作成功！",titleAndContent);
}

    @PostMapping("/queryPageList")
    public AjaxResult QueryPageList(@Valid ArticleVo articleVo) {
        Article article=new Article();
        BeanUtils.copyProperties(articleVo,article);
        Pageable pageable =  PageRequest.of(articleVo.getPageNum(),articleVo.getPageSize());
        Page<Article> articlePage=  articleService.queryPageList(article,pageable);
        return AjaxResult.success("操作成功！",articlePage);
    }


    @PostMapping("/createIndex")
    public AjaxResult createIndex(@Valid Article article) {
        return AjaxResult.success("操作成功！",articleService.createIndex(article.getClass()));
    }
    @PostMapping("/deleteIndex")
    public AjaxResult deleteIndex(@Valid Article article) {
        return AjaxResult.success("操作成功！",articleService.deleteIndex(article.getClass()));
    }
}
