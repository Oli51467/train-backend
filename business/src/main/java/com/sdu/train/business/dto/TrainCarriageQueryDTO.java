package com.sdu.train.business.dto;

import com.sdu.train.common.dto.PageBaseDTO;
import lombok.Data;

@Data
public class TrainCarriageQueryDTO extends PageBaseDTO {

    private String trainCode;
}
