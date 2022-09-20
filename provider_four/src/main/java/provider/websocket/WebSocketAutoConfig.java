package provider.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
public class WebSocketAutoConfig implements WebSocketConfigurer {

    private static final int MAX_MESSAGE_SIZE = 20 * 1024;
    private static final long MAX_IDLE = 60 * 60 * 1000;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 注册websocket组件 添加处理器和拦截器
        // websocket是websocket服务器的请求路径可以自己定义 "/websocket.do"请求交给自定义WebSocketHandler处理
        registry.addHandler(new WebSocketHandler(), "/provider")
                // 指定自定义拦截器
                .addInterceptors(new WebSocketHandshakeInterceptor())
                // 允许跨域
                .setAllowedOrigins("*");
        // 在某些低版本的浏览器中不支持websocket可以用下·sock-js替代
        registry.addHandler(new WebSocketHandler(), "/sock-js")
                .addInterceptors(new WebSocketHandshakeInterceptor())
                .setAllowedOrigins("*")
                // 开启sockJs支持
                .withSockJS();
    }

    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(MAX_MESSAGE_SIZE);
        container.setMaxBinaryMessageBufferSize(MAX_MESSAGE_SIZE);
        container.setMaxSessionIdleTimeout(MAX_IDLE);
        return container;
    }

}
