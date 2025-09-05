package com.sinse.electroshop.controller.shop;

import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.domain.Store;
import com.sinse.electroshop.websocket.dto.ChatMessage;
import com.sinse.electroshop.websocket.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/*
Spring 의 STOMP를 이용하면 웹소켓을 일반컨트롤러로 제어할 수 있다
마치 웹요청을 처리하듯...
*/
@Slf4j
@Controller
public class ChatController {

    //서버에 접속한 모든 유저 목록
    private Set<String> connectedUsrs=new ConcurrentHashMap<>().newKeySet();

    //서버에 존재하는 모든 방목록(상품의 수와 일치)
    private Map<String, ChatRoom> roomStorage=new ConcurrentHashMap<>();

    /*--------------------------------------------
    접속 요청 처리
    접속과 동시에 해당 상품과 관련된 방하나를 선택하고, 그 방에 참여한 고객목록을 반환
    --------------------------------------------*/
    @MessageMapping("/connect") //   localhost:9999/app/conntect
    @SendTo("/topic/users")
    public Set<String> connect(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        //SimpMessageHeaderAccessor 객체를 이용하면 WebSocket의 Session 에 들어있는 정보를 추출
        //HttpSession 에서 사용자 로그인 정보인 Member를 꺼내보자
        //STOMP 기반으로 HttpSession을 꺼내려면 인터셉터 객체를 구현 및 등록해야 함
        int product_id=Integer.parseInt(message.getContent());//상품의 pk
        ChatRoom chatRoom=null; //발견된 방

        if(headerAccessor.getSessionAttributes().get("member") !=null) { //일반 회원이라면..
            Member member = (Member) headerAccessor.getSessionAttributes().get("member");
            log.debug("웹소켓 Session에서 꺼낸 정보는 " + member.getName());
            //log.debug("클라이언트 접속과 동시에 보낸 메시지 " + message.getContent());

            //일반 회원은 개설된 방에 참여하면 됨..하지만, 일반회원이 들어갈 방을 알아야 함?
            for(ChatRoom room : roomStorage.values()){
                if(room.getProduct_id()==product_id){
                   chatRoom=room; //고객이 보고있는, 즉 참여중인 방 발견
                   break;
                }
            }
            chatRoom.getCustomers().add(member.getId());

        }else if(headerAccessor.getSessionAttributes().get("store") !=null){//상점회원이라면...
            Store store = (Store) headerAccessor.getSessionAttributes().get("store");
            log.debug("웹소켓 Session에서 꺼낸 정보는 " + store.getStoreName());
            //log.debug("클라이언트 접속과 동시에 보낸 메시지 " + message.getContent());


            //룸을 추가하기 전에 중복여부 판단하기
            boolean exist=false; //중복 여부를 판단할수 있는 기준 변수

            for(ChatRoom room : roomStorage.values()){
                if(room.getProduct_id()==product_id){ //해당 상품과 관련된 방 발견!!
                    exist=true;
                    chatRoom=room;
                    break;
                }
            }

            //중복이 없을때만 방 추가
            if (exist == false) {
                chatRoom = new ChatRoom();
                chatRoom.setRoomId(UUID.randomUUID().toString());//방의 구분 고유 ID
                chatRoom.setProduct_id(product_id); //어떤 상품에 대한 채팅방인지...
                chatRoom.getCustomers().add(store.getBusinessId());
                //생성된 방을 전체 룸 리스트에 추가하기
                roomStorage.put(chatRoom.getRoomId(), chatRoom);
            }
            //방 참여하기
            chatRoom.getCustomers().add(store.getBusinessId());
        }
        return chatRoom.getCustomers();
    }

    //메시지 요청 처리
    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")  ///topic/messages 를 구독한 모든 이들에게 전송
    public ChatMessage send(ChatMessage message) {

        log.debug(message.getSender()+"가 전송한 메시지 "+message.getContent());

        return message;
    }



}








