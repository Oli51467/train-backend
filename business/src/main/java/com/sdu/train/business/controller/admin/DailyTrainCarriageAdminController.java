package com.sdu.train.business.controller.admin;

import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.business.dto.DailyTrainCarriageQueryDTO;
import com.sdu.train.business.dto.DailyTrainCarriageSaveDTO;
import com.sdu.train.business.viewObject.DailyTrainCarriageVO;
import com.sdu.train.business.service.DailyTrainCarriageService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/daily-train-carriage")
public class DailyTrainCarriageAdminController {

    @Resource
    private DailyTrainCarriageService dailyTrainCarriageService;

    @PostMapping("/save")
    public ResponseResult save(@Valid @RequestBody DailyTrainCarriageSaveDTO req) {
        dailyTrainCarriageService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/query-list")
    public ResponseResult queryList(@Valid DailyTrainCarriageQueryDTO req) {
        PageResponse<DailyTrainCarriageVO> list = dailyTrainCarriageService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        dailyTrainCarriageService.delete(id);
        return ResponseResult.ok();
    }

}