package com.example.configuration;

import com.example.camunda.DecisionPostEvaluationListener;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.delegate.DmnDecisionEvaluationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class DmnConfiguration {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private DmnEngine dmnEngine;

    @Bean
    public DmnEngine createDmnEngine() {
        DmnEngineConfiguration configuration = DmnEngineConfiguration
                .createDefaultDmnEngineConfiguration();
        DmnDecisionEvaluationListener dListener = new DecisionPostEvaluationListener();
        configuration
                .getCustomPostDecisionEvaluationListeners()
                .add(dListener);
        DmnEngine dmnEngine = configuration.buildEngine();
        return dmnEngine;
    }

    @Bean(name = "userDecision")
    public DmnDecision createDecisionEfterlon() {
        Resource resource = resourceLoader.getResource("classpath:dmn/user.dmn");
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dmnEngine.parseDecision("decision", is);
    }
}
