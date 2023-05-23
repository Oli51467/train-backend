package com.sdu.train.business.controller.admin;

import com.sdu.train.business.dto.TrainQueryDTO;
import com.sdu.train.business.dto.TrainSaveDTO;
import com.sdu.train.business.service.TrainService;
import com.sdu.train.business.viewObject.TrainVO;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.common.response.ResponseResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train")
public class TrainAdminController {

    @Resource
    private TrainService trainService;

    @PostMapping("/save/")
    public ResponseResult save(@Valid @RequestBody TrainSaveDTO req) {
        trainService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/getAll/")
    public ResponseResult queryList(@Valid TrainQueryDTO req) {
        PageResponse<TrainVO> list = trainService.queryList(req);
        return ResponseResult.ok(list);
    }

    @GetMapping("/get/")
    public ResponseResult queryAll() {
        return trainService.queryAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        trainService.delete(id);
        return ResponseResult.ok();
    }

}