package pers.oliverwee.chatroom.web.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * websorcket
 *
 * @author Weiws
 */
public class SocketHandler extends TextWebSocketHandler {

    /**
     * socket 连接成功后.
     *
     * @param webSocketSession session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        String userId = (String) webSocketSession.getAttributes().get("userId");
        String roomId = (String) webSocketSession.getAttributes().get("roomId");
        RoomManager.joinRoom(webSocketSession, userId, roomId);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {

    }

    /**
     * 连接断开后操作.
     * @param webSocketSession session
     * @param closeStatus 断开状态
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        String userId = (String) webSocketSession.getAttributes().get("userId");
        String roomId = (String) webSocketSession.getAttributes().get("roomId");
        RoomManager.leaveRoom(userId, roomId);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
