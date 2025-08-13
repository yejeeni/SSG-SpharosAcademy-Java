package com.ssg.xmlapp.model.restaurant;

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
public class RestaurantHandler extends DefaultHandler {

    @Getter
    List<Restaurant> restaurants;

    Restaurant restaurant;
    
    // 실행부가 지나가고 있는 태그 파악
    private boolean isMainTitle = false;
    private boolean isPlace = false;
    private boolean isTitle = false;
    private boolean isSubTtitle = false;
    private boolean isItemcntnts = false;

    @Override
    public void startDocument() throws SAXException {
        log.debug("startDocument");
        restaurants = new ArrayList<Restaurant>();
    }

    // 시작 태그를 만났을 때
    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        log.debug("<{}>", tag);
        if (tag.equals("item")){
            restaurant = new Restaurant();
        } else if (tag.equals("MAIN_TITLE")) {
            isMainTitle = true;
        } else if (tag.equals("PLACE")) {
            isPlace = true;
        } else if (tag.equals("TITLE")) {
            isTitle = true;
        } else if (tag.equals("SUBTITLE")) {
            isSubTtitle = true;
        } else if (tag.equals("ITEMCNTNTS")) {
            isItemcntnts = true;
        }
    }

    // 태그 사이의 컨텐츠를 만났을 때
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        log.debug(content);
        if (isMainTitle) restaurant.setMainTitle(content);
        if (isPlace) restaurant.setPlace(content);
        if (isTitle) restaurant.setTitle(content);
        if (isSubTtitle) restaurant.setSubTitle(content);
        if (isItemcntnts) restaurant.setItemcntnts(content);
    }

    // 종료 태그를 만났을 때
    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        log.debug("</{}>", tag);
        if (tag.equals("item")){
            restaurants.add(restaurant);
        } else if (tag.equals("MAIN_TITLE")) {
            isMainTitle = false;
        } else if (tag.equals("PLACE")) {
            isPlace = false;
        } else if (tag.equals("TITLE")) {
            isTitle = false;
        } else if (tag.equals("SUBTITLE")) {
            isSubTtitle = false;
        } else if (tag.equals("ITEMCNTNTS")) {
            isItemcntnts = false;
        }
    }

    // 문서가 끝날 때
    @Override
    public void endDocument() throws SAXException {
        log.debug("파싱 결과: restaurants Size: {}", restaurants.size());
    }
}
