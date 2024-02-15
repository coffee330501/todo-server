package io.github.coffee330501.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("io.github.coffee330501.dao.mapper")
public class MybatisConfig {

}
