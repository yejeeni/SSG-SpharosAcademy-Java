package com.ssg.foodapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.foodapp.model.ApiResponse;
import com.ssg.foodapp.model.GbApiResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
public class FoodController {

    private String serviceKey = "oMSkyu%2Bb2VuK64aR0lHoMdmyncTCdZACXV46LXaIWNje0OudnzrOHHuAFUx6uczfAbNK6VGw7mUjkbro7Y04fQ%3D%3D";

    @GetMapping("/stores")
    public GbApiResponse getGBList(String searchKey) throws IOException, InterruptedException {
        String baseUrl = "http://apis.data.go.kr/5080000/gnrlRstrtService/getGnrlRstrt"; /*URL*/

        // 파라미터 설정
        String url = baseUrl + "?" +
                "serviceKey=" + "oMSkyu%2Bb2VuK64aR0lHoMdmyncTCdZACXV46LXaIWNje0OudnzrOHHuAFUx6uczfAbNK6VGw7mUjkbro7Y04fQ%3D%3D" +
                "&pageNo=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&numOfRows=" + URLEncoder.encode("10", StandardCharsets.UTF_8);
        log.warn("searchKey = {}", searchKey);
        if (searchKey != null && !searchKey.isEmpty()) {
            log.warn("searchKey = {}", searchKey);
            url += "&bssh_nm=" + URLEncoder.encode(searchKey, StandardCharsets.UTF_8);
        }

        // 요청 시도 객체
        HttpClient client = HttpClient.newHttpClient();

        // 요청 정보 객체
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        // Open API 서버에 요청 시도
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Open Api의 String 결과물을 클래스로 매핑시키기 위해 jackson을 이용
        ObjectMapper objectMapper = new ObjectMapper();
        GbApiResponse apiResponse = objectMapper.readValue(response.body(), GbApiResponse.class);

        return apiResponse;
    }


//    @GetMapping("/stores")
    public String getGBListOld() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/5080000/gnrlRstrtService/getGnrlRstrt"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=oMSkyu%2Bb2VuK64aR0lHoMdmyncTCdZACXV46LXaIWNje0OudnzrOHHuAFUx6uczfAbNK6VGw7mUjkbro7Y04fQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("induty_nm","UTF-8") + "=" + URLEncoder.encode("일반음식점", "UTF-8")); /*업종명*/
//        urlBuilder.append("&" + URLEncoder.encode("bssh_nm","UTF-8") + "=" + URLEncoder.encode("정마담식당", "UTF-8")); /*업소명*/
//        urlBuilder.append("&" + URLEncoder.encode("locplc(rn)","UTF-8") + "=" + URLEncoder.encode("경상북도 구미시 선산읍 선산중앙로18길 16", "UTF-8")); /*소재지(도로명)*/
//        urlBuilder.append("&" + URLEncoder.encode("locplc(lnm)","UTF-8") + "=" + URLEncoder.encode("경상북도 구미시 선산읍 동부리 380", "UTF-8")); /*소재지(지번)*/
//        urlBuilder.append("&" + URLEncoder.encode("locplc_telno","UTF-8") + "=" + URLEncoder.encode("054-481-2353", "UTF-8")); /*소재지전화번호*/
//        urlBuilder.append("&" + URLEncoder.encode("mgc_telno","UTF-8") + "=" + URLEncoder.encode("054-480-5548", "UTF-8")); /*관리기관전화번호*/
//        urlBuilder.append("&" + URLEncoder.encode("mgc_nm","UTF-8") + "=" + URLEncoder.encode("경상북도 구미시청", "UTF-8")); /*관리기관명*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

        return sb.toString();
    }

    //    @GetMapping("/stores")
    public String getListOld(String store_name) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6430000/cbRecreationalFoodInfoService/getRecreationalFoodInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("currentPage", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
//            urlBuilder.append("&" + URLEncoder.encode("CMPNM","UTF-8") + "=" + URLEncoder.encode(store_name, "UTF-8")); /*상호명*/
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
    }



//    @GetMapping("/stores")
    public ApiResponse getCBList(String store_name) throws IOException, InterruptedException {
        String baseUrl = "http://apis.data.go.kr/6430000/cbRecreationalFoodInfoService/getRecreationalFoodInfo"; /*URL*/

        // 파라미터 설정
        String url = baseUrl + "?" +
                "serviceKey=" + serviceKey +
                "&currentPage=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&perPage=" + URLEncoder.encode("20", StandardCharsets.UTF_8) +
                "&CMPNM=" + URLEncoder.encode(store_name, StandardCharsets.UTF_8);

        // 요청 시도 객체
        HttpClient client = HttpClient.newHttpClient();
        
        // 요청 정보 객체
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        // Open API 서버에 요청 시도
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Open Api의 String 결과물을 클래스로 매핑시키기 위해 jackson을 이용
        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);

        return apiResponse;
    }
}