package com.example.camunda;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.dmn.engine.delegate.DmnDecisionEvaluationEvent;
import org.camunda.bpm.dmn.engine.delegate.DmnDecisionEvaluationListener;

@Slf4j
public class DecisionPostEvaluationListener implements DmnDecisionEvaluationListener {
    @Override
    public void notify(DmnDecisionEvaluationEvent dmnDecisionEvaluationEvent) {
        log.info("Current DMN rules: {}", dmnDecisionEvaluationEvent.getDecisionResult().getDecision());
    }
}
