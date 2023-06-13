package com.example;

import com.example.model.OrganizationCheckRequest;
import com.example.repository.CheckResultRepository;
import com.example.service.DmnServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DmnTests {

    @MockBean
    CheckResultRepository checkResultRepository;

    @Autowired
    DmnServiceImpl dmnService;

    @Test
    public void testValidAllFactors() {
        OrganizationCheckRequest dto = new OrganizationCheckRequest(
                false, false, false, false, 49);
        Set<String> failedFactors = dmnService.runValidate(dto);
        Assert.assertTrue(failedFactors.size() == 0);
    }

    @Test
    public void testFailEGRULAddressFrod() {
        OrganizationCheckRequest dto = new OrganizationCheckRequest(
                true, false, false, false, 49);
        Set<String> failedFactors = dmnService.runValidate(dto);
        Assert.assertTrue(failedFactors.size() == 1);
        Assert.assertTrue(failedFactors.contains("EGRULAddressFrodFactor"));
    }

    @Test
    public void testFailEGRULAddressFrodAndContractPriceDowngradePercent() {
        OrganizationCheckRequest dto = new OrganizationCheckRequest(
                true, false, false, false, 70);
        Set<String> failedFactors = dmnService.runValidate(dto);
        Assert.assertTrue(failedFactors.size() == 2);
        Assert.assertTrue(failedFactors.contains("EGRULAddressFrodFactor"));
        Assert.assertTrue(failedFactors.contains("contractPriceDowngradePercentFactor"));
    }
}
