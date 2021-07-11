package pers.oliverwee.chatroom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.AbstractMessageBrokerConfiguration;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import pers.oliverwee.chatroom.web.interceptor.WebSocketInterceptor;
import pers.oliverwee.chatroom.web.websocket.SocketHandler;

/**
 * websocket配置类.
 *
 * @author Weiws
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    /**
     * @param webSocketHandlerRegistry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(SocketHandler(), "/chatroom")
                .addInterceptors(WebSocketInterceptor())
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketInterceptor WebSocketInterceptor() {
        return new WebSocketInterceptor();
    }

    @Bean
    public SocketHandler SocketHandler() {
        return new SocketHandler();
    }

}
