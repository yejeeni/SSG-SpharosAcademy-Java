package com.ssg.xmlapp.model.busStop;

import com.ssg.xmlapp.domain.BusStop;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * xml을 자바로 변환
 */
@Slf4j
@Component
public class BusStopHandler extends DefaultHandler {
    @Getter
    List<BusStop> busStops;
    BusStop busStop;

    // 실행부가 지나가고 있는 태그 파악
    private boolean isBstopid = false;
    private boolean isBstopnm = false;
    private boolean isArsno = false;
    private boolean isGpsx = false;
    private boolean isGpsy = false;
    private boolean isStoptype = false;

    // 문서가 시작될 때
    @Override
    public void startDocument() throws SAXException {
        log.debug("startDocument");
        busStops = new ArrayList<BusStop>();
    }

    // 시작 태그를 만났을 때
    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        log.debug("<{}>", tag);
        // member를 만나면 Model 올리기
        if (tag.equals("items")){
            BusStop busStop = new BusStop();
        } else if (tag.equals("bstopid")) {
            isBstopid = true;
        } else if (tag.equals("bstopnm")) {
            isBstopnm = true;
        } else if (tag.equals("arsno")) {
            isArsno = true;
        } else if (tag.equals("gpsx")) {
            isGpsx = true;
        } else if (tag.equals("gpsy")) {
            isGpsy = true;
        } else if (tag.equals("stoptype")) {
            isStoptype = true;
        }
    }

    // 태그 사이의 컨텐츠를 만났을 때
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        log.debug(content);
        if (isBstopid) busStop.setBstopid(Integer.parseInt(content));
        if (isBstopnm) busStop.setBstopnm(content);
        if (isArsno) busStop.setArsno(content);
        if (isGpsx) busStop.setGpsx(Integer.parseInt(content));
        if (isGpsy) busStop.setGpsx(Integer.parseInt(content));
        if (isStoptype) busStop.setStoptype(content);
    }

    // 종료 태그를 만났을 때
    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        log.debug("</{}>", tag);
        if (tag.equals("items")){
            busStops.add(busStop);
        } else if (tag.equals("bstopid")) {
            isBstopid = false;
        } else if (tag.equals("bstopnm")) {
            isBstopnm = false;
        } else if (tag.equals("arsno")) {
            isArsno = false;
        } else if (tag.equals("gpsx")) {
            isGpsx = false;
        } else if (tag.equals("gpsy")) {
            isGpsy = false;
        } else if (tag.equals("stoptype")) {
            isStoptype = false;
        }
    }

    // 문서가 끝날 때
    @Override
    public void endDocument() throws SAXException {
        log.debug("파싱 결과: busStops Size: {}", busStops.size());
    }
}
