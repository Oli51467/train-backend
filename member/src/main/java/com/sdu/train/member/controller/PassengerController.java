package com.sdu.train.member.controller;

import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.member.dto.PassengerDTO;
import com.sdu.train.member.service.PassengerService;
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
}
