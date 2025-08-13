package com.ssg.xmlapp.model.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberHandler memberHandler;

    /**
     * 파싱 시도
     */
    public List<Member> parse() throws Exception {
        try {
            // 정적 자원 접근 (프로젝트 내 resources 디렉토리)
            ClassPathResource resource = new ClassPathResource("static/member.xml");
            File file = resource.getFile();

            // SAXParser 생성
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // 파일을 대상으로 파싱 시작
            saxParser.parse(file, memberHandler);
            log.debug("파싱 완료");

            return memberHandler.getMemberList();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // 구체적인 예외를 잡아서 MemberException으로 감싸서 던짐
//            throw new MemberException("멤버 XML 파싱 중 오류 발생", e);
            return null;
        }
    }
}
