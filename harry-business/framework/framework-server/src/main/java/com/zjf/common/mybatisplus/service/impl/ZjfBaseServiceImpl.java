package com.zjf.common.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.zjf.common.mybatisplus.InsertNoKeyGenerator;
import com.zjf.common.mybatisplus.service.ZjfBaseService;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * @desc:
 **/
public class ZjfBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements ZjfBaseService<T> {

    /**
     * @Descript 需要重写原因为,ms sqlserver 驱动在批量插入中与db3keyGenerator
     *  重写方法,使用NoKeyGenerator,在自增长类型中,批量插入中不返回主键
     *  注意：如果需要得到insert的对象的id，只能采用save方法，不能用saveBatch
     *  故只适用于批量插入 且不关心插入对象id的场景
     * @param entityList
    * @param batchSize
     * @return boolean
     */
    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }

        int i = 0;
        String sqlStatement = SqlHelper.table(super.currentModelClass()).getSqlStatement(InsertNoKeyGenerator.INSERT_NO_KEY_GENERATOR);
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            for (T anEntityList : entityList) {
                batchSqlSession.insert(sqlStatement, anEntityList);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }

    /**
     * @Descript 重写原因同  saveBatch
     * @param entityList
    * @param batchSize
     * @return boolean
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        Class<?> cls = currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        int i = 0;
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            for (T anEntityList : entityList) {
                if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                    Object idVal = ReflectionKit.getMethodValue(cls, anEntityList, tableInfo.getKeyProperty());
                    if (StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal))) {
                        //---------------update-------------
                        batchSqlSession.insert(SqlHelper.table(currentModelClass()).getSqlStatement(InsertNoKeyGenerator.INSERT_NO_KEY_GENERATOR), anEntityList);
                    } else {
                        MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                        param.put(Constants.ENTITY, anEntityList);
                        batchSqlSession.update(sqlStatement(SqlMethod.UPDATE_BY_ID), param);
                    }
                    //不知道以后会不会有人说更新失败了还要执行插入
                    if (i >= 1 && i % batchSize == 0) {
                        batchSqlSession.flushStatements();
                    }
                    i++;
                } else {
                    throw ExceptionUtils.mpe("Error:  Can not execute. Could not find @TableId.");
                }
                batchSqlSession.flushStatements();
            }
        }
        return true;
    }
}
