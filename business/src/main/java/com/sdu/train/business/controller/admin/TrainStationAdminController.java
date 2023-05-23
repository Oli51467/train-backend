package com.sdu.train.business.controller.admin;

import com.sdu.train.business.dto.TrainStationQueryDTO;
import com.sdu.train.business.dto.TrainStationSaveDTO;
import com.sdu.train.business.service.TrainStationService;
import com.sdu.train.business.viewObject.TrainStationVO;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.common.response.ResponseResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train-station")
public class TrainStationAdminController {

    @Resource
    private TrainStationService trainStationService;

    @PostMapping("/save/")
    public ResponseResult save(@Valid @RequestBody TrainStationSaveDTO req) {
        trainStationService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/getAll/")
    public ResponseResult queryList(@Valid TrainStationQueryDTO req) {
        PageResponse<TrainStationVO> list = trainStationService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        trainStationService.delete(id);
        return ResponseResult.ok();
    }

}