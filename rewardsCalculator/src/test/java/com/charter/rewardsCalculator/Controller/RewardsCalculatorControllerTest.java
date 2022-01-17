package com.charter.rewardsCalculator.Controller;

import com.charter.rewardsCalculator.Service.CsvReader;
import com.charter.rewardsCalculator.Service.RewardsCalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class RewardsCalculatorControllerTest {

// In Real world these can be mocked with Mokito mock test and the service response can be stubbed
    CsvReader csvReader = new CsvReader();
    RewardsCalculatorService rewardsCalculatorService = new RewardsCalculatorService(csvReader);
    RewardsCalculatorController rewardsCalculatorController = new RewardsCalculatorController(rewardsCalculatorService);

    @Test
    void calculateRewardsByCustomer() {
        var result = rewardsCalculatorController.calculateRewardsByCustomer();
        assertEquals(HttpStatus.OK ,result.getStatusCode());
        assertEquals(2720, result.getBody().get("1"));
        assertEquals(2795, result.getBody().get("2"));
    }

    @Test
    void calculateRewardsByMonth() {
        var result = rewardsCalculatorController.calculateRewardsByMonth();
        assertEquals(HttpStatus.OK ,result.getStatusCode());
        assertEquals(815, result.getBody().get("2").get(Month.JANUARY) );
        assertEquals(790, result.getBody().get("1").get(Month.JANUARY) );
    }
}