package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Document(indexName = "article",type = "doc",shards = 2, replicas = 1)//标记为文档类型，indexName：对应索引库名称type：对应在索引库中的类型，shards：分片数量，默认5，replicas：副本数量，默认1
public class Article implements Serializable  {

        private static final long serialVersionUID = 6320548148250372657L;

        @Id
        private String id;
        //标题
        private String title;
        //摘要
        private String abstracts;
        //内容
        private String content;
        //时间
        //@Field(type = FieldType.Date, format = DateFormat.basic_date)yyyy-MM-dd HH：mm：ss
        @DateTimeFormat(pattern = "yyyy-MM-dd HH：mm：ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH：mm：ss", timezone = "GMT+8")
        private Date time;

}
