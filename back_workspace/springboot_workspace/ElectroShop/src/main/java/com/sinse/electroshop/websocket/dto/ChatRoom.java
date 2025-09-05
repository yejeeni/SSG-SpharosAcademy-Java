package com.sinse.electroshop.websocket.dto;

import lombok.Data;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/*
* 채팅방 정보를 담는 DTO 객체
* 1) 채팅방의 고유값
* 2) 채팅방의 참여자 목록 Set
* 3) 어떤상품에 대한 채팅방인지..
* */
@Data
public class ChatRoom {
    private String roomId; //채팅방의 고유값
    private int product_id;  //어떤상품에 대한 채팅방인지..
    private Set<String> customers=new ConcurrentHashMap<>().newKeySet(); //채팅방 참여 고객들..
}
