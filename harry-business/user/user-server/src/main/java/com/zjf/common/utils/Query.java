package com.zjf.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjf.common.constants.Constant;
import com.zjf.common.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 查询参数
 * @author harry.zhang
 */
public class Query<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long currPage = 1;
        long pageSize = 10;

        if(params.get(Constant.CURR_PAGE) != null && !"".equals((String)params.get(Constant.CURR_PAGE))){
            currPage = Long.parseLong((String)params.get(Constant.CURR_PAGE));
        }
        if(params.get(Constant.PAGE_SIZE) != null && !"".equals((String)params.get(Constant.PAGE_SIZE))){
            pageSize = Long.parseLong((String)params.get(Constant.PAGE_SIZE));
        }

        //分页对象
        Page<T> page = new Page<>(currPage, pageSize);

        //分页参数
        params.put(Constant.CURR_PAGE, page);

        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject((String)params.get(Constant.ORDER_FIELD));
        String order = (String)params.get(Constant.ORDER);

        //前端字段排序
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
            if(Constant.ASC.equalsIgnoreCase(order)) {
                return page.setAsc(orderField);
            }else {
                return page.setDesc(orderField);
            }
        }

        //默认排序
        if(isAsc) {
            page.setAsc(defaultOrderField);
        }else {
            page.setDesc(defaultOrderField);
        }

        return page;
    }
}
