package com.sdu.train.business.viewObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class TrainVO {

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 车次编号
     */
    private String code;
    /**
     * 车次类型|枚举[TrainTypeEnum]
     */
    private String type;
    /**
     * 始发站
     */
    private String start;
    /**
     * 始发站拼音
     */
    private String startPinyin;
    /**
     * 出发时间
     */
    @JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8")
    @NotNull(message = "【出发时间】不能为空")
    private Date startTime;
    /**
     * 终点站
     */
    private String end;
    /**
     * 终点站拼音
     */
    private String endPinyin;
    /**
     * 到站时间
     */
    @JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8")
    @NotNull(message = "【到站时间】不能为空")
    private Date endTime;
    /**
     * 新增时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
