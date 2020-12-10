package com.corn.springboot.start.websocket.controller;

import com.corn.springboot.start.websocket.common.MyClusterWebSocketHandler;
import com.corn.springboot.start.websocket.common.MyWebSocketHandler;
import com.corn.springboot.start.websocket.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: SendMsgController
 * @Package com.corn.sprngboot.websocket.demo.controller
 * @Description: TODO
 * @date 2020/9/29 9:17
 */
@RestController
@RequestMapping("/websocket")
public class SendMsgController {

//    @Autowired
//    private MyNettyWebSocketHandler myNettyWebSocketHandler;

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Autowired
    private MyClusterWebSocketHandler myClusterWebSocketHandler;


    @RequestMapping("/sendMsg")
    public void sendMsgToUser(@RequestParam("roomId") String roomId,
                              @RequestParam("userId") String userId,
                              @RequestParam("message")String message){
        try {
            MessageDto dto = new MessageDto();
            dto.setUserId(userId);
            dto.setRoomId(roomId);
            dto.setMessage(message);
            myClusterWebSocketHandler.sendMessageToOne(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/getTotal")
    public String getTotal(){
        int count = MyWebSocketHandler.getOnlineCount();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "total:"+count;
    }
}
