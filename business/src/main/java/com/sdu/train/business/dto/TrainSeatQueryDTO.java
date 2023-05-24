package com.sdu.train.business.dto;

import com.sdu.train.common.dto.PageBaseDTO;
import lombok.Data;

@Data
public class TrainSeatQueryDTO extends PageBaseDTO {

    private String trainCode;

    @Override
    public String toString() {
        return "TrainSeatQueryDTO{" +
                "} " + super.toString();
    }
}
