package com.sdu.train.business.controller.admin;

import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.business.dto.DailyTrainSeatQueryDTO;
import com.sdu.train.business.dto.DailyTrainSeatSaveDTO;
import com.sdu.train.business.viewObject.DailyTrainSeatVO;
import com.sdu.train.business.service.DailyTrainSeatService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/daily-train-seat")
public class DailyTrainSeatAdminController {

    @Resource
    private DailyTrainSeatService dailyTrainSeatService;

    @PostMapping("/save")
    public ResponseResult save(@Valid @RequestBody DailyTrainSeatSaveDTO req) {
        dailyTrainSeatService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/query-list")
    public ResponseResult queryList(@Valid DailyTrainSeatQueryDTO req) {
        PageResponse<DailyTrainSeatVO> list = dailyTrainSeatService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        dailyTrainSeatService.delete(id);
        return ResponseResult.ok();
    }

}