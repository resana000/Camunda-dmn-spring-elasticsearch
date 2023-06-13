package com.example.controller;

import com.example.business.DmnService;
import com.example.model.OrganizationCheckRequest;
import com.example.model.OrganizationCheckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dmn")
@RequiredArgsConstructor
public class DmnController {

    private final DmnService dmnService;

    @PostMapping(value = "/check")
    public OrganizationCheckResponse validate(
            @RequestBody OrganizationCheckRequest dto) {
        return dmnService.validateAndSave(dto);
    }

}
