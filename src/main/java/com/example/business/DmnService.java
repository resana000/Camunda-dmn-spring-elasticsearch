package com.example.business;

import com.example.model.OrganizationCheckDto;

import java.util.Set;

public interface DmnService {

    public Set<String> validateAndSave(OrganizationCheckDto dto);
}
