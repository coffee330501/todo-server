package io.github.coffee330501;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.coffee330501.module.entity.BaseEntity;

public class Codegen {

    public static void main(String[] args) {
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/todo_test?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("objcfeng");
        globalConfig.setBasePackage("io.github.coffee330501");
        globalConfig.setEntitySuperClass(BaseEntity.class);
        globalConfig.setLogicDeleteColumn("delete_time");
        // 忽略字段
        globalConfig.getStrategyConfig().setIgnoreColumns("id", "create_time", "update_time", "delete_time", "version");

        // 各生成项配置
        generateEntity(globalConfig);
        generateMapper(globalConfig);
        generateMapperXml(globalConfig);
        generateController(globalConfig);
        generateService(globalConfig);
        generateServiceImpl(globalConfig);

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static void generateEntity(GlobalConfig globalConfig) {
        globalConfig.setEntityPackage("io.github.coffee330501.module.entity");
        //设置表前缀和只生成哪些表
        globalConfig.setGenerateTable("todo_list", "user");

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);

        // 乐观锁
        globalConfig.setVersionColumn("version");

        globalConfig.setEntityOverwriteEnable(true);
    }

    public static void generateMapper(GlobalConfig globalConfig) {
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.setMapperOverwriteEnable(true);
        globalConfig.setMapperPackage("io.github.coffee330501.dao.mapper");
    }

    public static void generateMapperXml(GlobalConfig globalConfig) {
        globalConfig.setMapperXmlGenerateEnable(true);
    }

    public static void generateController(GlobalConfig globalConfig) {
        globalConfig.setControllerGenerateEnable(true);
    }

    public static void generateService(GlobalConfig globalConfig) {
        globalConfig.setServiceGenerateEnable(true);
    }

    public static void generateServiceImpl(GlobalConfig globalConfig) {
        globalConfig.setServiceImplGenerateEnable(true);
    }
}
