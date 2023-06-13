package com.example.business;

import com.example.model.OrganizationCheckRequest;
import com.example.model.OrganizationCheckResponse;

public interface DmnService {

    OrganizationCheckResponse validateAndSave(OrganizationCheckRequest dto);
}
