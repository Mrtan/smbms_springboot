package com.kuang.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Provider {
    private Integer id;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proContact;
    private String ProPhone;
    private String proAddress;
    private String proFax;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
}
