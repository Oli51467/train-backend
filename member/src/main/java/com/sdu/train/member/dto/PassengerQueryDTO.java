package com.sdu.train.member.dto;

import com.sdu.train.common.dto.PageBaseDTO;
import lombok.Data;

@Data
public class PassengerQueryDTO extends PageBaseDTO {

    private Long memberId;
}
