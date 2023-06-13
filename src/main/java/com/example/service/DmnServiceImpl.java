package com.example.service;

import com.example.business.DmnService;
import com.example.entity.CheckResult;
import com.example.model.OrganizationCheckRequest;
import com.example.model.OrganizationCheckResponse;
import com.example.repository.CheckResultRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DmnServiceImpl implements DmnService {

    private final DmnEngine dmnEngine;
    private final CheckResultRepository checkResultRepository;
    private final DmnDecision userDecision;

    @Override
    public OrganizationCheckResponse validateAndSave(OrganizationCheckRequest dto) {
        Set<String> failedFactors = runValidate(dto);
        checkResultRepository.save(new CheckResult(dto.toString(), new ArrayList<>(failedFactors)));
        return new OrganizationCheckResponse(
                failedFactors.isEmpty(),
                failedFactors
        );
    }


    public Set<String>  runValidate(OrganizationCheckRequest dto) {
        Map<String, Object> variableMap = new ObjectMapper().convertValue(dto, new TypeReference<Map<String, Object>>() {
                });

        DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(userDecision, variableMap);
        Set<String> failedFactors = new HashSet<>();

        if (result.size() == 1) {
            log.info("All checks true");
        } else {
            failedFactors = result.getResultList().stream()
                    .filter(a -> !a.containsKey("default"))
                    .flatMap(b -> b.keySet().stream())
                    .collect(Collectors.toSet());
            log.debug("Failed factors: {}", failedFactors);
        }

        return failedFactors;
    }
}
