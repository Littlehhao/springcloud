package com.sztech.cleangovern.model.helper;


import java.util.List;

/**
 * @program: cleangovern
 * @description: 分页查询条件封装类
 * @author: jiefu
 * @create: 2018-08-13 19:27
 **/
public class PageSearcher {

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 每页多少条
     *
     */
    private Integer pageSize = 10;


    private int total;//总条数

    private List list;//每页显示的数据

    private int star;//开始数据

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
