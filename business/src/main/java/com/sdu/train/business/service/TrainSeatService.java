package com.sdu.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdu.train.business.domain.TrainCarriage;
import com.sdu.train.business.domain.TrainSeat;
import com.sdu.train.business.domain.TrainSeatExample;
import com.sdu.train.business.dto.TrainSeatQueryDTO;
import com.sdu.train.business.dto.TrainSeatSaveDTO;
import com.sdu.train.business.enums.SeatColEnum;
import com.sdu.train.business.mapper.TrainSeatMapper;
import com.sdu.train.business.viewObject.TrainSeatVO;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.common.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainSeatService {

    private static final Logger logger = LoggerFactory.getLogger(TrainSeatService.class);

    @Resource
    private TrainSeatMapper trainSeatMapper;

    @Resource
    private TrainCarriageService trainCarriageService;

    public void save(TrainSeatSaveDTO req) {
        DateTime now = DateTime.now();
        TrainSeat trainSeat = BeanUtil.copyProperties(req, TrainSeat.class);
        if (ObjectUtil.isNull(trainSeat.getId())) {
            trainSeat.setId(SnowUtil.getSnowflakeNextId());
            trainSeat.setCreateTime(now);
            trainSeat.setUpdateTime(now);
            trainSeatMapper.insert(trainSeat);
        } else {
            trainSeat.setUpdateTime(now);
            trainSeatMapper.updateByPrimaryKey(trainSeat);
        }
    }

    public PageResponse<TrainSeatVO> queryList(TrainSeatQueryDTO req) {
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        trainSeatExample.setOrderByClause("train_code asc, carriage_index asc, carriage_seat_index asc");
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getTrainCode())) {
            criteria.andTrainCodeEqualTo(req.getTrainCode());
        }

        logger.info("查询页码：{}", req.getPage());
        logger.info("每页条数：{}", req.getPageSize());
        PageHelper.startPage(req.getPage(), req.getPageSize());
        List<TrainSeat> trainSeatList = trainSeatMapper.selectByExample(trainSeatExample);

        PageInfo<TrainSeat> pageInfo = new PageInfo<>(trainSeatList);
        logger.info("总行数：{}", pageInfo.getTotal());
        logger.info("总页数：{}", pageInfo.getPages());

        List<TrainSeatVO> list = BeanUtil.copyToList(trainSeatList, TrainSeatVO.class);

        PageResponse<TrainSeatVO> resp = new PageResponse<>();
        resp.setTotal(pageInfo.getTotal());
        resp.setList(list);
        return resp;
    }

    public void delete(Long id) {
        trainSeatMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void generateTrainSeat(String trainCode) {
        DateTime now = DateTime.now();
        // 1.清空当前车次下的所有的座位记录
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        criteria.andTrainCodeEqualTo(trainCode);
        trainSeatMapper.deleteByExample(trainSeatExample);
        // 2.查找当前车次下的所有的车厢
        List<TrainCarriage> carriageList = trainCarriageService.selectByTrainCode(trainCode);
        logger.info("当前车次下的车厢数：{}", carriageList.size());
        // 3.循环生成每个车厢的座位
        for (TrainCarriage trainCarriage : carriageList) {
            // 3.1拿到车厢数据：行数、座位类型(得到列数)
            Integer rowCount = trainCarriage.getRowCount();
            String seatType = trainCarriage.getSeatType();
            int seatIndex = 1;
            // 3.2根据车厢的座位类型，筛选出所有的列，比如车箱类型是一等座，则筛选出columnList={ACDF}
            List<SeatColEnum> colEnumList = SeatColEnum.getColsByType(seatType);
            // 3.3循环行数
            for (int row = 1; row <= rowCount; row ++ ) {
                for (SeatColEnum seatColEnum : colEnumList) {
                    // 构造座位数据并保存数据库
                    TrainSeat trainSeat = new TrainSeat();
                    trainSeat.setId(SnowUtil.getSnowflakeNextId());
                    trainSeat.setTrainCode(trainCode);
                    trainSeat.setCarriageIndex(trainCarriage.getIndex());
                    trainSeat.setRow(StrUtil.fillBefore(String.valueOf(row), '0', 2));
                    trainSeat.setCol(seatColEnum.getCode());
                    trainSeat.setSeatType(seatType);
                    trainSeat.setCarriageSeatIndex(seatIndex ++);
                    trainSeat.setCreateTime(now);
                    trainSeat.setUpdateTime(now);
                    trainSeatMapper.insert(trainSeat);
                }
            }
        }
    }
}
