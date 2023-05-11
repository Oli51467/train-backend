package com.sdu.train.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberDTO {

    @NotBlank(message = "【手机号】不能为空")
    private String mobile;
}
