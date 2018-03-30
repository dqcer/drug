package com.dqcer.drug.web.utils;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:分页工具类</p>
 * @author administrator
 * @date 2018/3/30 8:51
 * @param
 * @return
 */
public class PageUtil<T> implements Serializable{

    private static final long serialVersionUID = -4728743688206893581L;

    /**
     * 总数量
     */
    private Integer totalCount = 0;

    /**
     * 每页数量 -默认10000条
     */
    private Integer pageSize = 10000;

    /**
     * 当前页 -默认为首页
     */
    private  Integer currentPage = 1;

    /**
     * 总页数
     */
    private Integer totalPage = 0;

    /**
     * 是否有前一页
     */
    private Boolean hasPrePage = false;

    /**
     * 是否有下一页
     */
    private Boolean hasNextPage = false;

    private List<T> result = new ArrayList<T>();

    /**
     * 分页查询参数
     */
    @JSONField(serialize = false)
    private Object conditions = new Object();

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Integer getTotalPage() {
        if (this.totalCount == 0) {
            this.totalPage = 1;
        } else if (this.totalCount % this.pageSize == 0) {
            this.totalPage = this.totalCount / this.pageSize;
        } else {
            this.totalPage = (this.totalCount / this.pageSize) + 1;
        }
        return this.totalPage;
    }

    public Boolean getHasPrePage() {
        this.hasPrePage = currentPage > 1 ? true : false;
        return this.hasPrePage;
    }

    public Boolean getHasNextPage() {
        this.hasNextPage = currentPage < getTotalPage() ? true : false;
        return this.hasNextPage;
    }

    public Object getConditions() {
        return conditions;
    }

    public void setConditions(Object conditions) {
        this.conditions = conditions;
    }
}
