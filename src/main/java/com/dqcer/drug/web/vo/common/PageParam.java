package com.dqcer.drug.web.vo.common;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * <p>Description:基础参数</p>
 * @author administrator
 * @date 2018/3/30 9:12
 * @param
 * @return
 */
public class PageParam implements Serializable{

    private static final long serialVersionUID = -8334506300006664476L;

    public static Integer DEFAULT_PAGE_SIZE = 10000;

    public static Integer DEFAULT_PAGE_NUMBER = 1;

    /**
     * 每页条数默认20
     */
    @Min(value = 1, message = "每页数据条数最小为1")
    private Integer pageSize;

    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序类型（DESC,ASC）
     */
    private String sort;

    /**
     * 数据查询开始索引
     */
    private Integer beginIndex;

    public Integer getPageSize() {
        return (null == pageSize ? DEFAULT_PAGE_SIZE : pageSize);
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

    public Integer getBeginIndex() {
        currentPage = (null != currentPage) ? currentPage : DEFAULT_PAGE_NUMBER;
        beginIndex = (currentPage >= DEFAULT_PAGE_NUMBER ? currentPage - 1 : currentPage)
                * (null == pageSize ? DEFAULT_PAGE_SIZE : pageSize);
        return beginIndex;
    }

    public void setBeginIndex(Integer beginDataIndex) {
        this.beginIndex = beginDataIndex;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
