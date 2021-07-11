package pers.oliverwee.chatroom.web.websocket;

import org.springframework.web.socket.WebSocketSession;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聊天室控制类.
 *
 * @author Weiws
 */
public class RoomManager {
    /**
     * 用来存放接入的用户 key是房间id value是用户集合.
     */
    private static Map<String, Set<String>> concurrentHashMap = new ConcurrentHashMap<String, Set<String>>();

    /**
     * 进入房间操作.
     *
     * @param session session
     * @param userId  用户id
     * @param roomId  房间id
     * @return 房间用户id集合
     */
    public static void joinRoom(WebSocketSession session,
                                @PathParam(value = "userId") String userId,
                                @PathParam(value = "roomId") String roomId) {
        if (concurrentHashMap.containsKey(roomId)) {
            // 房间存在，用户加入
            concurrentHashMap.get(roomId).add(userId);
        } else if (!concurrentHashMap.containsKey(roomId)) {
            // 房间不存在，创建房间
            Set<String> room = new HashSet<>();
            room.add(userId);
            concurrentHashMap.put(roomId, room);
        }
    }

    /**
     * 离开的操作.
     *
     * @param userId 用户id
     * @param roomId 房间id
     */
    public static void leaveRoom(@PathParam(value = "userId") String userId,
                          @PathParam(value = "roomId") String roomId) {
        if (concurrentHashMap.containsKey(roomId)) {
            // 房间存在，用户移除
            concurrentHashMap.get(roomId).remove(userId);
            // 房间人数为空，房间移除
            if (concurrentHashMap.get(roomId).size() < 1) {
                concurrentHashMap.remove(roomId);
            }
        }
    }

    /**
     * 获得房间全部用户id
     *
     * @param roomId 房间id
     * @return 房间全部用户id
     */
    public static Set<String> getAllUserByRoom(String roomId) {
        return concurrentHashMap.getOrDefault(roomId, Collections.EMPTY_SET);
    }
}
