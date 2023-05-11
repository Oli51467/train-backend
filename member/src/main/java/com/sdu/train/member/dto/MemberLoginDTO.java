package com.sdu.train.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberLoginDTO {

    @NotBlank(message = "【手机号】不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式错误")
    private String mobile;

    @NotBlank(message = "【验证码】不能为空")
    private String code;
}
