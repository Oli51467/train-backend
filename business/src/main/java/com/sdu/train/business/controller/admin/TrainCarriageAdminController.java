package com.sdu.train.business.controller.admin;

import com.sdu.train.business.dto.TrainCarriageQueryDTO;
import com.sdu.train.business.dto.TrainCarriageSaveDTO;
import com.sdu.train.business.service.TrainCarriageService;
import com.sdu.train.business.viewObject.TrainCarriageVO;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.common.response.ResponseResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train-carriage")
public class TrainCarriageAdminController {

    @Resource
    private TrainCarriageService trainCarriageService;

    @PostMapping("/save/")
    public ResponseResult save(@Valid @RequestBody TrainCarriageSaveDTO req) {
        trainCarriageService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/getAll/")
    public ResponseResult queryList(@Valid TrainCarriageQueryDTO req) {
        PageResponse<TrainCarriageVO> list = trainCarriageService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        trainCarriageService.delete(id);
        return ResponseResult.ok();
    }

}