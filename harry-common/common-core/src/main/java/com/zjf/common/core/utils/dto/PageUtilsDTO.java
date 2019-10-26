package com.zjf.common.core.utils.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页DTO
 * @author Harry
 */
@Data
public class PageUtilsDTO<T> implements Serializable {
    /**
     * 总记录数
     */
    private Long totalCount;
    /**
     * 每页记录数
     */
    private Long pageSize;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 当前页数
     */
    private Long currPage;
    /**
     * 列表数据
     */
    private List<T> list;

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param currPage    当前页数
     */
    public PageUtilsDTO(List<T> list, Long totalCount, Long pageSize, Long currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (long)Math.ceil((double)totalCount/pageSize);
    }

}

