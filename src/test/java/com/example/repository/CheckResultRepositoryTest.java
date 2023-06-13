package com.example.repository;

import com.example.TestElasticsearchContainer;
import com.example.entity.CheckResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.Collections;
import java.util.List;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Profile("integration")
public class CheckResultRepositoryTest {

    @Container
    private static ElasticsearchContainer elasticsearchContainer = new TestElasticsearchContainer();

    @BeforeAll
    static void setUp() {
        elasticsearchContainer.start();
    }

    @AfterAll
    static void destroy() {
        elasticsearchContainer.stop();
    }

    @Autowired
    private CheckResultRepository checkResultRepository;


    @Test
    public void test2PopulateElasticSearch() {
        log.info("elasticsearchContainer is running: {}", elasticsearchContainer.isRunning());

        CheckResult checkResult = new CheckResult();
        checkResult.setPayload("json");
        checkResult.setFailedChecks(Collections.singletonList("factor"));
        CheckResult saved = checkResultRepository.save(checkResult);

        List<CheckResult> list = checkResultRepository.findAll();
        Assertions.assertTrue(list.size() == 1);
        Assertions.assertEquals(list.get(0), saved);
    }

}