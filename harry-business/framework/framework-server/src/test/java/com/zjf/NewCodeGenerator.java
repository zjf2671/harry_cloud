package com.zjf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * mybatis-plus逆向工程，3.0.6版本
 */

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class NewCodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        final String packageName = "gen1"; //模块名,这个会生成gen1目录到modules这个目录下面
        //需要生成的表名
        final String[] tableNames = {
                "gen_test1"
//                ,"gen_test2"
        };
        genExecute(scanner("模块名（这个会生成目录到modules这个目录下面）"), scanner("表名，多个英文逗号分割").split(","));
    }

    private static void genExecute(String packageName, String[] tableNames){
        final DbType DB_TYPE = DbType.SQL_SERVER;
        final String DB_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/gen?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        final String DB_USER_NAME = "root";
        final String DB_PASSWORD = "123456";

        final String PACKAGE_NAME = "modules." + packageName;

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir")+"\\"+System.getProperty("module.dir");
        if(projectPath!=null){
            gc.setOutputDir(projectPath+"/src/main/java");
        }else {
            gc.setOutputDir("D://逆向工程");
        }
        // 作者
        gc.setAuthor("harry");
        gc.setOpen(false);
        // 开启swagger2
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        gc.setMapperName("%sDao");  //设置mapper名称以Dao结尾  "%s"+"Dao"
//        gc.setEntityName("%sEntity");
        gc.setServiceName("%sService");
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DB_TYPE);
        dsc.setUrl(DB_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(DB_USER_NAME);
        dsc.setPassword(DB_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(PACKAGE_NAME);
        pc.setParent("com.zjf");
        pc.setMapper("dao");
        pc.setController("controller.manager");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName().replace(".","/")
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        focList.add(new FileOutConfig("/templates/entityVO.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/"+ pc.getParent().replace(".","/") +"/vo/"
                        + tableInfo.getEntityName()+"VO" + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 模板设置
        TemplateConfig tc = new TemplateConfig();
//        tc.setEntity("templates/entity.java");
//        tc.setService("templates/service.java");
//        tc.setServiceImpl("templates/serviceImpl.java");
//        tc.setMapper("templates/mapper.java");
        tc.setXml(null);
        mpg.setTemplate(tc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        // 开启lombok
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(tableNames);
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        // 逻辑删除
        strategy.setLogicDeleteFieldName("isDeleted");
        strategy.setSuperServiceClass("com.zjf.common.mybatisplus.service.ZjfBaseService");
        strategy.setSuperServiceImplClass("com.zjf.common.mybatisplus.service.impl.ZjfBaseServiceImpl");
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}