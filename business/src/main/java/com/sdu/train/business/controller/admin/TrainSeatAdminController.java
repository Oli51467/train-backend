package com.sdu.train.business.controller.admin;

import com.sdu.train.business.dto.TrainSeatQueryDTO;
import com.sdu.train.business.dto.TrainSeatSaveDTO;
import com.sdu.train.business.service.TrainSeatService;
import com.sdu.train.business.viewObject.TrainSeatVO;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.common.response.ResponseResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train-seat")
public class TrainSeatAdminController {

    @Resource
    private TrainSeatService trainSeatService;

    @PostMapping("/save/")
    public ResponseResult save(@Valid @RequestBody TrainSeatSaveDTO req) {
        trainSeatService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/getAll/")
    public ResponseResult queryList(@Valid TrainSeatQueryDTO req) {
        PageResponse<TrainSeatVO> list = trainSeatService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        trainSeatService.delete(id);
        return ResponseResult.ok();
    }

}