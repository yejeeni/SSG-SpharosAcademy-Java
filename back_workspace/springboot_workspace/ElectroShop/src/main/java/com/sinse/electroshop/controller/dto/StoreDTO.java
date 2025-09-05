package com.sinse.electroshop.controller.dto;

import lombok.Data;

//Data Transfer Object
@Data
public class StoreDTO {
    private int store_id;
    private String id;
    private String pwd;
    private String store_name;
}
