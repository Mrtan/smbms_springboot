package com.kuang.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Bill {
    private Integer id;
    private String billCode;
    private String productName;
    private String productDesc;
    private String productUnit;
    private Integer productCount;
    private Double totalPrice;
    private Integer isPayment;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private Integer providerId;

    @TableField(exist = false)
    private String proName;
}
