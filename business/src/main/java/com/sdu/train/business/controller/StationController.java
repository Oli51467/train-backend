package com.sdu.train.business.controller;

import com.sdu.train.business.service.StationService;
import com.sdu.train.business.viewObject.StationVO;
import com.sdu.train.common.response.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    @Resource
    private StationService stationService;

    @GetMapping("/getAll/")
    public ResponseResult queryList() {
        List<StationVO> list = stationService.queryAll();
        return ResponseResult.ok(list);
    }

}
