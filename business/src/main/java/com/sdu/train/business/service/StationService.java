package com.sdu.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.common.util.SnowUtil;
import com.sdu.train.business.domain.Station;
import com.sdu.train.business.domain.StationExample;
import com.sdu.train.business.mapper.StationMapper;
import com.sdu.train.business.dto.StationQueryDTO;
import com.sdu.train.business.dto.StationSaveDTO;
import com.sdu.train.business.viewObject.StationVO;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private static final Logger logger = LoggerFactory.getLogger(StationService.class);

    @Resource
    private StationMapper stationMapper;

    public void save(StationSaveDTO req) {
        DateTime now = DateTime.now();
        Station station = BeanUtil.copyProperties(req, Station.class);
        if (ObjectUtil.isNull(station.getId())) {
            station.setId(SnowUtil.getSnowflakeNextId());
            station.setCreateTime(now);
            station.setUpdateTime(now);
            stationMapper.insert(station);
        } else {
            station.setUpdateTime(now);
            stationMapper.updateByPrimaryKey(station);
        }
    }

    public PageResponse<StationVO> queryList(StationQueryDTO req) {
        StationExample stationExample = new StationExample();
        stationExample.setOrderByClause("id desc");
        StationExample.Criteria criteria = stationExample.createCriteria();

        logger.info("查询页码：{}", req.getPage());
        logger.info("每页条数：{}", req.getPageSize());
        PageHelper.startPage(req.getPage(), req.getPageSize());
        List<Station> stationList = stationMapper.selectByExample(stationExample);

        PageInfo<Station> pageInfo = new PageInfo<>(stationList);
        logger.info("总行数：{}", pageInfo.getTotal());
        logger.info("总页数：{}", pageInfo.getPages());

        List<StationVO> list = BeanUtil.copyToList(stationList, StationVO.class);

        PageResponse<StationVO> resp = new PageResponse<>();
        resp.setTotal(pageInfo.getTotal());
        resp.setList(list);
        return resp;
    }

    public void delete(Long id) {
        stationMapper.deleteByPrimaryKey(id);
    }
}
