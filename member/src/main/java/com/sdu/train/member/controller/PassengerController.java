package com.sdu.train.member.controller;

import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.viewObject.PageResponse;
import com.sdu.train.member.dto.PassengerDTO;
import com.sdu.train.member.dto.PassengerQueryDTO;
import com.sdu.train.member.service.PassengerService;
import com.sdu.train.member.viewObject.PassengerVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping( "/passenger/save")
    @ResponseBody
    public ResponseResult register(@Valid @RequestBody PassengerDTO passengerDTO) {
        passengerService.savePassenger(passengerDTO);
        return ResponseResult.ok();
    }

    @GetMapping( "/passenger/get")
    @ResponseBody
    public ResponseResult getPassengerList(@Valid PassengerQueryDTO data) {
        data.setMemberId(LoginMemberContext.getId());
        PageResponse<PassengerVO> passengers = passengerService.getPassengerList(data);
        return ResponseResult.ok(passengers);
    }

    @DeleteMapping("/passenger/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        passengerService.delete(id);
        return ResponseResult.ok();
    }
}
