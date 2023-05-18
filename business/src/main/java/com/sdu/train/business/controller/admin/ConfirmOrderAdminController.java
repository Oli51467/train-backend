package com.sdu.train.business.controller.admin;

import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.PageResponse;
import com.sdu.train.business.dto.ConfirmOrderQueryDTO;
import com.sdu.train.business.dto.ConfirmOrderSaveDTO;
import com.sdu.train.business.viewObject.ConfirmOrderVO;
import com.sdu.train.business.service.ConfirmOrderService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/confirm-order")
public class ConfirmOrderAdminController {

    @Resource
    private ConfirmOrderService confirmOrderService;

    @PostMapping("/save")
    public ResponseResult save(@Valid @RequestBody ConfirmOrderSaveDTO req) {
        confirmOrderService.save(req);
        return ResponseResult.ok();
    }

    @GetMapping("/query-list")
    public ResponseResult queryList(@Valid ConfirmOrderQueryDTO req) {
        PageResponse<ConfirmOrderVO> list = confirmOrderService.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        confirmOrderService.delete(id);
        return ResponseResult.ok();
    }

}