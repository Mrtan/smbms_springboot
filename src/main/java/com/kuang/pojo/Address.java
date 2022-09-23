package com.kuang.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Address {
    private Integer id;
    private String contact;
    private String addressDesc;
    private String postCode;
    private String tel;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private Integer userId;
}
