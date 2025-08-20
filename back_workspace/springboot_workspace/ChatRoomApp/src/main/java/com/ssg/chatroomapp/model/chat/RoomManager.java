package com.ssg.chatroomapp.model.chat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoomManager {
    private static Map<String, String> roomMasterMap = new ConcurrentHashMap<>();

    public static void setRoomMaster(String roomUuid, String masterId) {
        roomMasterMap.put(roomUuid, masterId);
    }

    public static String getRoomMaster(String roomUuid) {
        return roomMasterMap.get(roomUuid);
    }

    public static void removeRoom(String roomUuid) {
        roomMasterMap.remove(roomUuid);
    }
}
