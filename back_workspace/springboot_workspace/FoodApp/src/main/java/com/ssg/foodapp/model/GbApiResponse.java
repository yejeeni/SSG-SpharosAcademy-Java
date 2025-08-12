package com.ssg.foodapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GbApiResponse {

    private List<Body> body;

    private Header header;

    @Getter
    @Setter
    public static class Body {
        @JsonProperty("induty_nm")
        private String indutyNm;

        @JsonProperty("bssh_nm")
        private String bsshNm;

        @JsonProperty("locplc(rn)")
        private String locplcRn;

        @JsonProperty("locplc(lnm)")
        private String locplcLnm;

        @JsonProperty("locplc_telno")
        private String locplcTelno;

        @JsonProperty("mgc_telno")
        private String mgcTelno;

        @JsonProperty("mgc_nm")
        private String mgcNm;
    }

    @Getter
    @Setter
    public static class Header {
        @JsonProperty("pageNo")
        private int pageNo;

        @JsonProperty("resultCode")
        private String resultCode;

        @JsonProperty("totalCount")
        private int totalCount;

        @JsonProperty("numOfRows")
        private int numOfRows;

        @JsonProperty("resultMsg")
        private String resultMsg;
    }
}
