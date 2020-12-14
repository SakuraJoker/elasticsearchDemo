package com.example.demo.entity.base;


import org.springframework.util.StringUtils;

/**
 * 分页数据
 *
 * @author ruoyi
 */
public class PageEntity
{
    /** 当前记录起始索引 */
    private int pageNum;
    /** 每页显示记录数 */
    private int pageSize;

    private  int count;

    public int getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getPageNum()
    {
        if (StringUtils.isEmpty(pageNum) || pageNum < 0){
            return 0;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getPageSize()
    {
        if (StringUtils.isEmpty(pageSize) || pageSize < 0||pageSize==0){
            return 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }


}

