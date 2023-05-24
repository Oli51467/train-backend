package com.sdu.train.business.controller.admin;

import com.sdu.train.business.dto.TrainQueryDTO;
import com.sdu.train.business.dto.TrainSaveDTO;
import com.sdu.train.business.service.TrainSeatService;
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

    @Resource
    private TrainSeatService trainSeatService;

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

    /**
     * 为某趟车次生成车座信息
     * @param trainCode 车次code
     * @return ResponseResult
     */
    @GetMapping("/seat/generate/{trainCode}")
    public ResponseResult generateSeat(@PathVariable String trainCode) {
        trainSeatService.generateTrainSeat(trainCode);
        return ResponseResult.ok();
    }

}