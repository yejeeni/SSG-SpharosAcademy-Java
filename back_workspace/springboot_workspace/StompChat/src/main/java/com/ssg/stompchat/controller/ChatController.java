package com.ssg.stompchat.controller;

import com.ssg.stompchat.ChatMessage;
import com.ssg.stompchat.model.chat.model.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
public class ChatController {
    private Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet(); // 서버에 접속한 유저 목록
    private Map<String, ChatRoom> roomStorage = new ConcurrentHashMap<>(); // 서버에 생성된 방 목록

    /**
     * 클라이언트의 접속 처리
     * "/app/connect" 접속 시 실행
     */
    @MessageMapping("/connect")
    @SendTo("/topic/users")
    public Set<String> getConnectedUsers(ChatMessage chatMessage){
        log.debug("클라이언트의 접속 요청 받음");
        connectedUsers.add(chatMessage.getSender());
        return connectedUsers;
    }

    /**
     * 클라이언트의 메시지 전송 처리
     */
    @MessageMapping("/chat.send")
    @SendTo("/topic/messages") // /topic/message를 구독 중인 클라이언트들에게만 전송
    public ChatMessage send(ChatMessage chatMessage){
        return chatMessage;
    }

    /**
     * 방 조회 메시지 전송 처리
     */
    @MessageMapping("/room.list")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> listRoom(){
        log.debug("listRoom");
        return roomStorage.values();
    }

    /**
     * 방 만들기 메시지 전송 처리
     */
    @MessageMapping("/room.create")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> createRoom(ChatMessage chatMessage){
        ChatRoom chatRoom = new ChatRoom();
        String roomId = UUID.randomUUID().toString();
        chatRoom.setRoomId(roomId);
        chatRoom.setRoomName(chatMessage.getContent());

        // 생성된 방을 목록에 추가
        roomStorage.put(roomId, chatRoom);

        return roomStorage.values();
    }

    /**
     * 방 참여 요청 메시지 처리
     */
    @MessageMapping("/room.enter")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> enterRoom(ChatMessage chatMessage){
        ChatRoom chatRoom = roomStorage.get(chatMessage.getRoomId());

        if (chatRoom != null){
            chatRoom.getUsers().add(chatMessage.getSender());
        }

        return roomStorage.values();
    }

    /**
     * 방 나가기 요청 메시지 처리
     */
    @MessageMapping("/room.leave")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> leaveRoom(ChatMessage chatMessage) {
        ChatRoom chatRoom = roomStorage.get(chatMessage.getRoomId());
        if (chatRoom != null){
            chatRoom.getUsers().remove(chatMessage.getSender());
        }
        return roomStorage.values();
    }

    /**
     * 접속 해제 메시지 요청 처리
     */
    @MessageMapping("/disconnect")
    @SendTo("/topic/users")
    public Set<String> disconnect(ChatMessage chatMessage){
        log.debug("disconnect");
        connectedUsers.remove(chatMessage.getSender());
        return connectedUsers;
    }

    /**
     * favicon 처리
     */
    @GetMapping("favicon.ico")
    @ResponseBody
    public void favicon(){

    }
}
