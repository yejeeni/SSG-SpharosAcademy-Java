package com.ssg.chatroomapp.model.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.chatroomapp.domain.Member;
import com.ssg.chatroomapp.dto.Room;
import com.ssg.chatroomapp.dto.RoomResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@NoArgsConstructor
@ServerEndpoint(value="/chat/multi", configurator = HttpSessionConfigurator.class)
public class ChatEndpoint {

    private static Set<Session> userList = new HashSet<>(); // 서버에서 필요한 접속자 명단

    private static Set<Member> memberList = new HashSet<>(); // 클라이언트에 전달할 접속자 정보
    private static Set<Room> roomList = new HashSet<>(); // 클라이언트에 전달할 방 정보

    private static ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void opOpen(Session session, EndpointConfig config) throws JsonProcessingException {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        if (httpSession != null){
            // 클라이언트 브라우저에는 접속자 명단만 필요하므로 Member 모델에서 id만 추출
            Member member = (Member) httpSession.getAttribute("member");
            session.getUserProperties().put("member", member);
            userList.add(session);

            // 회원 정보 채우기
            Member obj = new Member();
            obj.setId(member.getId());
            obj.setName(member.getName());
            obj.setEmail(member.getEmail());
            memberList.add(obj); // 접속자 명단에 채우기

            // 연결 응답 정보 생성
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setResponseType("userConnected");
            roomResponse.setMemberList(memberList);
            roomResponse.setRoomList(roomList);

            // 접속한 클라이언트가 알아야할 정보 전송(접속자, ROOM 정보)
            String json = objectMapper.writeValueAsString(roomResponse);
            session.getAsyncRemote().sendText(json);

            log.debug("사용자 연결됨. 전송된 방 개수: {}", roomList.size());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws JsonProcessingException {
        log.debug(message);

        // 파싱
        JsonNode jsonNode = objectMapper.readTree(message);
        String requestType = jsonNode.get("requestType").asText();
        if (requestType.equals("createRoom")){ // 방 생성 요청일 경우
            log.debug("방 생성 예정");
            String userId = jsonNode.get("userId").asText();
            String roomName = jsonNode.get("roomName").asText();

            Member member = (Member) session.getUserProperties().get("member");
            if (!member.getId().equals(userId)){ // 로그인 시 사용된 HttpSession에 들어있는 정보와 웹소켓을 통해 전달된 정보가 일치하지 않는 경우
                // TODO 내부구현
            } else{
                UUID uuid = UUID.randomUUID(); // 생성한 방에 배정될 uuid
                Room room = new Room();
                room.setUUID(uuid.toString());
                room.setMasterId(userId);
                room.setRoomName(roomName);

                // 참여자 목록
                Set users = new HashSet();
                // 회원 정보 채우기
                Member obj = new Member();
                obj.setId(member.getId());
                obj.setName(member.getName());
                obj.setEmail(member.getEmail());
                users.add(obj); // 방장을 참여자에 추가
                room.setUsers(users);

                roomList.add(room);

                /**
                 * 클라이언트에 전송할 응답
                 */
                RoomResponse roomResponse = new RoomResponse();
                roomResponse.setResponseType("createRoom");
                roomResponse.setMemberList(memberList);
                roomResponse.setRoomList(roomList);
                log.debug("방 생성 정보: {}", room.getUUID());

                String responseJson = objectMapper.writeValueAsString(roomResponse);

                // 모든 연결된 클라이언트에게 전송
                for (Session userSession : userList) {
                    if (userSession.isOpen()) {
                        try {
                            userSession.getAsyncRemote().sendText(responseJson);
                        } catch (Exception e) {
                            log.error("메시지 전송 실패", e);
                        }
                    }
                }
                log.debug("방 생성 완료. 총 방 개수: {}", roomList.size());
            }
        } else if (requestType.equals("joinRoom")) {

        } else if (requestType.equals("exitRoom")) {

        } else if (requestType.equals("chat")) {

        }
    }
}
