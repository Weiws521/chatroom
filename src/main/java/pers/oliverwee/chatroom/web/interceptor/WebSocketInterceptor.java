package pers.oliverwee.chatroom.web.interceptor;

import cn.hutool.http.HttpUtil;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 聊天室拦截器.
 *
 * @author Weiws
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * 请求前.
     *
     * @param serverHttpRequest  请求体
     * @param serverHttpResponse 响应体
     * @param webSocketHandler   socket处理器
     * @param map                属性域
     * @return 请求结果
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest,
                                   ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler,
                                   Map<String, Object> map) {
        // 获得请求参数
        Map<String, String> paramMap = getParameter(serverHttpRequest.getURI().getQuery());
        String userId = paramMap.get("userId");
        String roomId = paramMap.get("roomId");
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(roomId)) {
            return false;
        }
        map.put("userId", userId);
        map.put("roomId", roomId);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }

    private Map<String, String> getParameter(String contents) {
        Map<String, String> map = new HashMap<>();
        String[] keyValues = contents.split("&");
        for (int i = 0; i < keyValues.length; i++) {
            String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
            String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);
            map.put(key, value);
        }
        return map;
    }
}
