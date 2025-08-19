package com.ssg.xmlapp.model.restaurant;

import com.ssg.xmlapp.exception.RestaurantException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantHandler restaurantHandler;
    private String serviceKey = "nNOBuNwKr7Qv0kMJ1JWwm9VEF%2F45LYS4%2Fn2G4SwwQ0zQ9KxOcNlugQhrcOELgYw7m4U9OgLaZHbPisCCFiGp5w%3D%3D";

    /**
     * 데이터 받아오기
     */
    public String getList() {
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/FoodService/getFoodKr"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
//        urlBuilder.append("&" + URLEncoder.encode("UC_SEQ","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*콘텐츠 ID*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());

            return sb.toString();
        } catch (IOException e) {
            throw new RestaurantException("음식점 API 호출 실패", e);
        }
    }

    /**
     * 파싱 시도
     */
    public List<Restaurant> parse() {
        try {
            // 정적 자원 접근 (프로젝트 내 resources 디렉토리)
            ClassPathResource resource = new ClassPathResource("static/member.xml");
            File file = resource.getFile();

            // SAXParser 생성
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // 파일을 대상으로 파싱 시작
            saxParser.parse(file, restaurantHandler);
            log.debug("파싱 완료");

            return restaurantHandler.getRestaurants();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // 구체적인 예외를 잡아서 MemberException으로 감싸서 던짐
            throw new RestaurantException("음식점 XML 파싱 중 오류 발생", e);
        }
    }

    // XML 문자열 파싱 메서드
    public List<Restaurant> parseFromXmlString(String xml) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // 문자열을 Reader로 감싸서 InputSource 생성
            InputSource inputSource = new InputSource(new StringReader(xml));

            // 기존 restaurants 리스트가 남아있으면 초기화
            restaurantHandler.startDocument();

            saxParser.parse(inputSource, restaurantHandler);
            log.debug("파싱 완료");

            return restaurantHandler.getRestaurants();
        } catch (ParserConfigurationException | SAXException| IOException e) {
            throw new RestaurantException("문자열 XML 파싱 중 오류 발생", e);
        }
    }

    public void testException() {
        throw new RestaurantException("테스트용 강제 예외 발생!");
    }

}
