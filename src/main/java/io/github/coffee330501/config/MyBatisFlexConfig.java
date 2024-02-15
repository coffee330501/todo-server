package io.github.coffee330501.config;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.logicdelete.impl.DateTimeLogicDeleteProcessor;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyBatisFlexConfig implements MyBatisFlexCustomizer, ConfigurationCustomizer {
    @Override
    public void customize(FlexConfiguration flexConfiguration) {
        // 开启审计功能
        AuditManager.setAuditEnable(true);
        // 设置 SQL 审计收集器(mybatis-flex内置方案)
        AuditManager.setMessageCollector(auditMessage ->
                log.info("\nSQL日志:----------->\nsql:    {}\nspend:   {}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime())
        );
        // 注册雪花算法ID生成策略
        KeyGeneratorFactory.register("SnowflakeId", new SnowflakeKeyGenerator());
        // 逻辑删除处理器
        LogicDeleteManager.setProcessor(new DateTimeLogicDeleteProcessor());
    }

    @Override
    public void customize(FlexGlobalConfig flexGlobalConfig) {

    }
}
