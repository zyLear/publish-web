package com.zylear.publish.web.config;


import com.zylear.publish.web.util.MybatisDataSourceConfigHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * @author 28444
 * @date 2018/1/10.
 */
@Configuration
@MapperScan(basePackages = DataSourcePublishConfig.SCAN_PACKAGE)
@EnableTransactionManagement
public class DataSourcePublishConfig {

    public final static String DATA_SOURCE = "dataSourcePublish";
    public final static String SCAN_PACKAGE = "com.zylear.publish.web.dao.mybatis.publish";
    public final static String SQL_SESSION_FACTORY = "sqlSessionFactoryPublish";
    public final static String TX_MANAGER = "txManagerPublish";
    public final static String SQL_SESSION_TEMPLATE = "sqlSessionTemplatePublish";   //don't set this is ok
    public final static List<String> XML_PATHS = Arrays.asList("classpath:com/zylear/publish/web/dao/mybatis/publish/*.xml");
    @Value("${database.publish.username}")
    private String username;
    @Value("${database.publish.password}")
    private String password;
    @Value("${database.publish.url}")
    private String url;
    @Value("${mybatis.config.path}")
    private String configPath;

    @Bean(DATA_SOURCE)
    public DataSource dataSource() {
        return MybatisDataSourceConfigHelper.createDruidDataSource(url, username, password);
    }

    @Bean(SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE) DataSource dataSource) {
        return MybatisDataSourceConfigHelper.getSqlSessionFactory(XML_PATHS, configPath, dataSource);
    }

    @Bean(TX_MANAGER)
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier(DATA_SOURCE) DataSource dataSource) {
        return MybatisDataSourceConfigHelper.getTransactionManager(dataSource);
    }

    @Bean(SQL_SESSION_TEMPLATE)
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
