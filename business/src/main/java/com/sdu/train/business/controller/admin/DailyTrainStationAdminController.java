package com.sdu.train.business.controller.admin;

import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.business.dto.DailyTrainStationQueryDTO;
import com.sdu.train.business.dto.DailyTrainStationSaveDTO;
import com.sdu.train.business.viewObject.DailyTrainStationVO;
import com.sdu.train.business.service.DailyTrainStationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/daily-train-station")
public class DailyTrainStationAdminController {

    @Resource
    private DailyTrainStationService dailyTrainStationService;

    @PostMapping("/save")
    public ResponseResult save(@Valid @RequestBody DailyTrainStationSaveDTO req) {
        dailyTrainStationService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/query-list")
    public ResponseResult queryList(@Valid DailyTrainStationQueryDTO req) {
        PageResponse<DailyTrainStationVO> list = dailyTrainStationService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        dailyTrainStationService.delete(id);
        return ResponseResult.ok();
    }

}