package com.sdu.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.util.SnowUtil;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.member.domain.Passenger;
import com.sdu.train.member.domain.PassengerExample;
import com.sdu.train.member.dto.PassengerDTO;
import com.sdu.train.member.dto.PassengerQueryDTO;
import com.sdu.train.member.mapper.PassengerMapper;
import com.sdu.train.member.viewObject.PassengerVO;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PassengerService")
public class PassengerService {

    private final Logger logger = LoggerFactory.getLogger(PassengerService.class);

    @Resource
    private PassengerMapper passengerMapper;

    public void savePassenger(PassengerDTO passengerDTO) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(passengerDTO, Passenger.class);
        if (ObjectUtil.isNull(passenger.getId())) {
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        } else {
            passenger.setUpdateTime(now);
            passengerMapper.updateByPrimaryKey(passenger);
        }
    }

    public PageResponse<PassengerVO> getPassengerList(PassengerQueryDTO data) {
        PassengerExample passengerExample = new PassengerExample();
        passengerExample.setOrderByClause("id desc");
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        criteria.andMemberIdEqualTo(data.getMemberId());
        // 分页插件 在sql语句之前startPage，该语句将自动插入下一个sql语句中分页
        PageHelper.startPage(data.getPage(), data.getPageSize());
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);

        // 自动获取总条数和总页数，使用PageResponse封装
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengerList);
        logger.info("总条数：{}", pageInfo.getTotal());
        logger.info("总页数: {}", pageInfo.getPages());
        List<PassengerVO> list = BeanUtil.copyToList(passengerList, PassengerVO.class);
        PageResponse<PassengerVO> resp = new PageResponse<>();
        resp.setTotal(pageInfo.getTotal());
        resp.setList(list);
        return resp;
    }

    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }
}
