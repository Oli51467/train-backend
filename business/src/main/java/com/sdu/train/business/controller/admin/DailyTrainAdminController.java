package com.sdu.train.business.controller.admin;

import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.business.dto.DailyTrainQueryDTO;
import com.sdu.train.business.dto.DailyTrainSaveDTO;
import com.sdu.train.business.viewObject.DailyTrainVO;
import com.sdu.train.business.service.DailyTrainService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/daily-train")
public class DailyTrainAdminController {

    @Resource
    private DailyTrainService dailyTrainService;

    @PostMapping("/save")
    public ResponseResult save(@Valid @RequestBody DailyTrainSaveDTO req) {
        dailyTrainService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/query-list")
    public ResponseResult queryList(@Valid DailyTrainQueryDTO req) {
        PageResponse<DailyTrainVO> list = dailyTrainService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        dailyTrainService.delete(id);
        return ResponseResult.ok();
    }

}