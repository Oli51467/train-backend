package com.sdu.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.util.SnowUtil;
import com.sdu.train.member.domain.Passenger;
import com.sdu.train.member.dto.PassengerDTO;
import com.sdu.train.member.mapper.PassengerMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("PassengerService")
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    public void savePassenger(PassengerDTO passengerDTO) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(passengerDTO, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
