package com.sdu.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdu.train.business.domain.Train;
import com.sdu.train.business.domain.TrainExample;
import com.sdu.train.business.dto.TrainQueryDTO;
import com.sdu.train.business.dto.TrainSaveDTO;
import com.sdu.train.business.mapper.TrainMapper;
import com.sdu.train.business.viewObject.TrainVO;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.exception.BusinessException;
import com.sdu.train.common.response.exception.ExceptionEnum;
import com.sdu.train.common.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    private static final Logger logger = LoggerFactory.getLogger(TrainService.class);

    @Resource
    private TrainMapper trainMapper;

    public void save(TrainSaveDTO req) {
        DateTime now = DateTime.now();
        Train train = BeanUtil.copyProperties(req, Train.class);
        if (ObjectUtil.isNull(train.getId())) {
            // 保存之前，先校验唯一键是否存在
            Train trainDB = selectByUnique(req.getCode());
            if (ObjectUtil.isNotEmpty(trainDB)) {
                throw new BusinessException(ExceptionEnum.BUSINESS_TRAIN_CODE_UNIQUE_ERROR);
            }
            train.setId(SnowUtil.getSnowflakeNextId());
            train.setCreateTime(now);
            train.setUpdateTime(now);
            trainMapper.insert(train);
        } else {
            train.setUpdateTime(now);
            trainMapper.updateByPrimaryKey(train);
        }
    }

    private Train selectByUnique(String code) {
        TrainExample trainExample = new TrainExample();
        trainExample.createCriteria()
                .andCodeEqualTo(code);
        List<Train> list = trainMapper.selectByExample(trainExample);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }


    public PageResponse<TrainVO> queryList(TrainQueryDTO req) {
        TrainExample trainExample = new TrainExample();
        trainExample.setOrderByClause("id desc");
        TrainExample.Criteria criteria = trainExample.createCriteria();

        logger.info("查询页码：{}", req.getPage());
        logger.info("每页条数：{}", req.getPageSize());
        PageHelper.startPage(req.getPage(), req.getPageSize());
        List<Train> trainList = trainMapper.selectByExample(trainExample);

        PageInfo<Train> pageInfo = new PageInfo<>(trainList);
        logger.info("总行数：{}", pageInfo.getTotal());
        logger.info("总页数：{}", pageInfo.getPages());

        List<TrainVO> list = BeanUtil.copyToList(trainList, TrainVO.class);

        PageResponse<TrainVO> resp = new PageResponse<>();
        resp.setTotal(pageInfo.getTotal());
        resp.setList(list);
        return resp;
    }

    public void delete(Long id) {
        trainMapper.deleteByPrimaryKey(id);
    }

    public ResponseResult queryAll() {
        TrainExample trainExample = new TrainExample();
        trainExample.setOrderByClause("code asc");
        List<Train> trainList = trainMapper.selectByExample(trainExample);
        return ResponseResult.ok(BeanUtil.copyToList(trainList, TrainVO.class));
    }
}
