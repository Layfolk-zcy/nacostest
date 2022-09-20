package provider.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: WebSocketConfig
 * @projectName: erukeribbon
 * @description: websocket配置
 * @date: 2022-08-26 10:58
 **/

@Configuration
public class WebSocketConfig {
    /**
     * ServerEndpointExporter 作用
     * <p>
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
