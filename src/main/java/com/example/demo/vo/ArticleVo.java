package com.example.demo.vo;

import com.example.demo.entity.base.PageEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleVo extends PageEntity {

    private String id;
    //标题
    private String title;
    //摘要
    private String abstracts;
    //内容
    private String content;
    //时间
    private Date time;
}
