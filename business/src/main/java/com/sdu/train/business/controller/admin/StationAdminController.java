package com.sdu.train.business.controller.admin;

import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.business.dto.StationQueryDTO;
import com.sdu.train.business.dto.StationSaveDTO;
import com.sdu.train.business.viewObject.StationVO;
import com.sdu.train.business.service.StationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/station")
public class StationAdminController {

    @Resource
    private StationService stationService;

    @PostMapping("/save")
    public ResponseResult save(@Valid @RequestBody StationSaveDTO req) {
        stationService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/query-list")
    public ResponseResult queryList(@Valid StationQueryDTO req) {
        PageResponse<StationVO> list = stationService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        stationService.delete(id);
        return ResponseResult.ok();
    }

}