package com.example.controller;

import com.example.business.DmnService;
import com.example.model.OrganizationCheckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dmn")
@RequiredArgsConstructor
public class DmnController {

    private final DmnService dmnService;

    @PostMapping(value = "/check")
    @ResponseBody
    public Iterable validate(
            @RequestBody OrganizationCheckDto dto) {
        return dmnService.validateAndSave(dto);
    }

}
