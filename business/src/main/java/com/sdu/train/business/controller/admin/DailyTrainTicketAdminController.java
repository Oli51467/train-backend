package com.sdu.train.business.controller.admin;

import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.business.dto.DailyTrainTicketQueryDTO;
import com.sdu.train.business.dto.DailyTrainTicketSaveDTO;
import com.sdu.train.business.viewObject.DailyTrainTicketVO;
import com.sdu.train.business.service.DailyTrainTicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/daily-train-ticket")
public class DailyTrainTicketAdminController {

    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @PostMapping("/save")
    public ResponseResult save(@Valid @RequestBody DailyTrainTicketSaveDTO req) {
        dailyTrainTicketService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/query-list")
    public ResponseResult queryList(@Valid DailyTrainTicketQueryDTO req) {
        PageResponse<DailyTrainTicketVO> list = dailyTrainTicketService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        dailyTrainTicketService.delete(id);
        return ResponseResult.ok();
    }

}