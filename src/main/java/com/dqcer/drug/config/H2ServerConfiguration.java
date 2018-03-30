package com.dqcer.drug.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * <p>Description:数据库配置类</p>
 * @author administrator
 * @date 2018/3/29 17:10
 * @param
 * @return
 */
@Configuration
public class H2ServerConfiguration {

    //tcp端口，默认9881
    @Value("${h2.tcp.port:9881}")
    private String h2TcpPort;

    //web端口，默认9882
    @Value("${h2.web.port:9882}")
    private  String h2WebPort;

    @Bean
    @ConditionalOnExpression("${h2.tcp.enabled:false}")
    public Server h2TcpServer() throws SQLException{
        return Server.createPgServer("-tcp","-tcpAllowOthers", "-tcpPort", h2TcpPort).start();
    }

    @Bean
    @ConditionalOnExpression("${h2.web.enabled:true}")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", h2WebPort).start();
    }


}
