package com.jiawa.train.${module}.controller.admin;

import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.resp.ResponseResult;
import com.sdu.train.common.resp.PageResponse;
import com.sdu.train.${module}.dto.${Domain}QueryDTO;
import com.sdu.train.${module}.dto.${Domain}SaveDTO;
import com.sdu.train.${module}.viewObject.${Domain}VO;
import com.sdu.train.${module}.service.${Domain}Service;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/${do_main}")
public class ${Domain}AdminController {

    @Resource
    private ${Domain}Service ${domain}Service;

    @PostMapping("/save")
    public ResponseResult save(@Valid @RequestBody ${Domain}SaveDTO req) {
        ${domain}Service.save(req);
        return new ResponseResult.ok();
    }

    @GetMapping("/query-list")
    public ResponseResult queryList(@Valid ${Domain}QueryDTO req) {
        PageResponse<${Domain}QueryDTO> list = ${domain}Service.queryList(req);
        return ResponseResult.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        ${domain}Service.delete(id);
        return PageResponse.ok();
    }

}