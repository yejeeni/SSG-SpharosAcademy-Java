package com.ssg.xmlapp.model.member;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 자바에서 xml을 파싱하는 법
 * 1. DOM 방식: 처리가 간단하나 메모리에 모든 요소를 돔으로 올려놓기 때문에 메모리 자원을 많이 사용
 * 2. SAX 방식: 실행부가 XML 문서를 위에서 아래로 진행하며 이벤트를 일으키며 관련 메서드를 호출
 */
@Slf4j
@Component
public class MemberHandler extends DefaultHandler {

    @Getter
    List<Member> memberList;

    Member member;
    
    // 실행부가 지나가고 있는 태그 파악
    private boolean isName = false;
    private boolean isAge = false;
    private boolean isJob = false;
    private boolean isTel = false;

    // 문서가 시작될 때
    @Override
    public void startDocument() throws SAXException {
        log.debug("startDocument");
        memberList = new ArrayList<Member>();
    }

    // 시작 태그를 만났을 때
    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        log.debug("<{}>", tag);
        // member를 만나면 Model 올리기
        if (tag.equals("member")){
            member = new Member();
        } else if (tag.equals("name")) {
            isName = true;
        } else if (tag.equals("age")) {
            isAge = true;
        } else if (tag.equals("job")) {
            isJob = true;
        } else if (tag.equals("tel")) {
            isTel = true;
        }
    }

    // 태그 사이의 컨텐츠를 만났을 때
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        log.debug(content);
        if (isName) member.setName(content);
        if (isAge) member.setAge(Integer.parseInt(content));
        if (isJob) member.setJob(content);
        if (isTel) member.setJob(content);
    }

    // 종료 태그를 만났을 때
    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        log.debug("</{}>", tag);
        if (tag.equals("member")){
            memberList.add(member);
        } else if (tag.equals("name")) {
            isName = false;
        } else if (tag.equals("age")) {
            isAge = false;
        } else if (tag.equals("job")) {
            isJob = false;
        } else if (tag.equals("tel")) {
            isTel = false;
        }
    }

    // 문서가 끝날 때
    @Override
    public void endDocument() throws SAXException {
        log.debug("파싱 결과: memberList Size: {}", memberList.size());
    }
}
