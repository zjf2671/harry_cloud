package com.zjf.common.mybatisplus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import static com.baomidou.mybatisplus.core.toolkit.StringPool.*;

/**
 * @desc: 为了解决 mssql 采用mybatisplus的saveBatch方法报错的问题  https://www.jianshu.com/p/49a613811a14
 **/
public class InsertNoKeyGenerator extends AbstractMethod {

    public final static String INSERT_NO_KEY_GENERATOR="insertNoKeyGenerator";
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = new NoKeyGenerator();
        SqlMethod sqlMethod = SqlMethod.INSERT_ONE;
        String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumn(false),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlProperty(false, null),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String keyProperty = null;
        String keyColumn = null;
        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                /** 自增主键 */
                //----delete-----删除配置 Jdbc3KeyGenerator 将不再获取自增主键
                //keyGenerator = new Jdbc3KeyGenerator();
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            } else {
                if (null != tableInfo.getKeySequence()) {
                    //--------update--------- 原来的代码为sqlMethod.getMethod(),  修改为  INSERT_NO_KEY_GENERATOR
                    keyGenerator = TableInfoHelper.genKeyGenerator(tableInfo, builderAssistant, INSERT_NO_KEY_GENERATOR, languageDriver);
                    keyProperty = tableInfo.getKeyProperty();
                    keyColumn = tableInfo.getKeyColumn();
                }
            }
        }
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        //--------update--------- 原来的代码为sqlMethod.getMethod(),  修改为  INSERT_NO_KEY_GENERATOR
        return this.addInsertMappedStatement(mapperClass, modelClass, INSERT_NO_KEY_GENERATOR, sqlSource, keyGenerator, keyProperty, keyColumn);
    }
}
