package com.ssg.foodapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Open API로부터 받은 응답 정보를 담기 위한 객체
 */
@Getter @Setter
public class ApiResponse {
    private List<Body> body;
    private Header header;

    // Body 내부 클래스
    @Getter @Setter
    public static class Body{
        @JsonProperty("SIGUN")
        private String SIGUN;

        @JsonProperty("LO")
        private double LO;

        @JsonProperty("MNMNU")
        private String MNMNU;

        @JsonProperty("SE")
        private String SE;

        @JsonProperty("CMPNM")
        private String CMPNM;

        @JsonProperty("MENU")
        private String MENU;

        @JsonProperty("TELNO")
        private String TELNO;

        @JsonProperty("_URL")
        private String _URL;

        @JsonProperty("ADRES")
        private String ADRES;

        @JsonProperty("LA")
        private String LA;

        @JsonProperty("TIME")
        private String TIME;

        @JsonProperty("DC")
        private String DC;
    }


    // Header
    @Getter @Setter
    public static class Header{
        private int perPage;
        private String resultCode;
        private int totalRows;
        private int currentPage;
        private String resultMsg;
    }
}
