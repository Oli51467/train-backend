package com.sdu.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.common.util.SnowUtil;
import com.sdu.train.business.domain.ConfirmOrder;
import com.sdu.train.business.domain.ConfirmOrderExample;
import com.sdu.train.business.mapper.ConfirmOrderMapper;
import com.sdu.train.business.dto.ConfirmOrderQueryDTO;
import com.sdu.train.business.dto.ConfirmOrderSaveDTO;
import com.sdu.train.business.viewObject.ConfirmOrderVO;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfirmOrderService {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmOrderService.class);

    @Resource
    private ConfirmOrderMapper confirmOrderMapper;

    public void save(ConfirmOrderSaveDTO req) {
        DateTime now = DateTime.now();
        ConfirmOrder confirmOrder = BeanUtil.copyProperties(req, ConfirmOrder.class);
        if (ObjectUtil.isNull(confirmOrder.getId())) {
            confirmOrder.setId(SnowUtil.getSnowflakeNextId());
            confirmOrder.setCreateTime(now);
            confirmOrder.setUpdateTime(now);
            confirmOrderMapper.insert(confirmOrder);
        } else {
            confirmOrder.setUpdateTime(now);
            confirmOrderMapper.updateByPrimaryKey(confirmOrder);
        }
    }

    public PageResponse<ConfirmOrderVO> queryList(ConfirmOrderQueryDTO req) {
        ConfirmOrderExample confirmOrderExample = new ConfirmOrderExample();
        confirmOrderExample.setOrderByClause("id desc");
        ConfirmOrderExample.Criteria criteria = confirmOrderExample.createCriteria();

        logger.info("查询页码：{}", req.getPage());
        logger.info("每页条数：{}", req.getPageSize());
        PageHelper.startPage(req.getPage(), req.getPageSize());
        List<ConfirmOrder> confirmOrderList = confirmOrderMapper.selectByExample(confirmOrderExample);

        PageInfo<ConfirmOrder> pageInfo = new PageInfo<>(confirmOrderList);
        logger.info("总行数：{}", pageInfo.getTotal());
        logger.info("总页数：{}", pageInfo.getPages());

        List<ConfirmOrderVO> list = BeanUtil.copyToList(confirmOrderList, ConfirmOrderVO.class);

        PageResponse<ConfirmOrderVO> resp = new PageResponse<>();
        resp.setTotal(pageInfo.getTotal());
        resp.setList(list);
        return resp;
    }

    public void delete(Long id) {
        confirmOrderMapper.deleteByPrimaryKey(id);
    }
}
