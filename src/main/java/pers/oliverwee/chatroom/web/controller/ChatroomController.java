package pers.oliverwee.chatroom.web.controller;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pers.oliverwee.chatroom.entity.ResultData;
import pers.oliverwee.chatroom.web.websocket.RoomManager;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 聊天室控制层.
 *
 * @author Weiws
 */
@Controller
@RequestMapping("/chatroom")
@Validated
public class ChatroomController {

    /**
     * 加入聊天室.
     *
     * @param roomId 聊天室id.
     * @param userId 用户id
     * @return 聊天室用户.
     */
    @GetMapping(value = "/joinRoom", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SendTo("")
    public ResultData<Set<String>> joinRoom(@RequestParam("roomId") String roomId,
                                            @RequestParam("userId") String userId) {
        RoomManager.joinRoom(null, userId,roomId);
        Set<String> userSet = RoomManager.getAllUserByRoom(roomId)
                .stream()
                .filter(userStreamId -> !userStreamId.equals(userId))
                .collect(Collectors.toSet());
        return ResultData.ok(userSet);
    }

    /**
     * 离开聊天室.
     *
     * @param roomId 聊天室id.
     * @param userId 用户id
     * @return 聊天室用户.
     */
    @GetMapping(value = "/leaveRoom", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultData<Set<String>> leaveRoom(@RequestParam("roomId") String roomId,
                                                       @RequestParam("userId") String userId) {
        RoomManager.leaveRoom(userId, roomId);
        Set<String> userSet = RoomManager.getAllUserByRoom(roomId)
                .stream()
                .filter(userStreamId -> !userStreamId.equals(userId))
                .collect(Collectors.toSet());
        return ResultData.ok(userSet);
    }
}
