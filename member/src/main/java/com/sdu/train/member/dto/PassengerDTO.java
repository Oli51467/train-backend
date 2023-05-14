package com.sdu.train.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class PassengerDTO {

    private Long id;

    @NotBlank(message = "【姓名】不能为空")
    private String name;

    @NotBlank(message = "【身份证】不能为空")
    private String idCard;

    @NotBlank(message = "【类型】不能为空")
    private String type;

    private Date createTime;

    private Date updateTime;
}
