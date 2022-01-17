package com.charter.rewardsCalculator.Service;

import org.junit.jupiter.api.Test;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RewardsCalculatorServiceTest {

CsvReader csvReader = new CsvReader();
RewardsCalculatorService rewardsCalculatorService = new RewardsCalculatorService(csvReader);

    @Test
    public void testRewardsByMonth(){
        var output = rewardsCalculatorService.calculateRewardsByMonth();
        assertTrue(output.size() > 0);
    }

    @Test
    public void testJanuaryOutputForCustomer1() {
        var output = rewardsCalculatorService.calculateRewardsByMonth();
        assertEquals(790, output.get("1").get(Month.JANUARY) );
    }

    @Test
    public void testJanuaryOutputForCustomer2() {
        var output = rewardsCalculatorService.calculateRewardsByMonth();
        assertEquals(815, output.get("2").get(Month.JANUARY) );
    }

    @Test
    public void testRewardsByCustomer() {
        var output = rewardsCalculatorService.calculateRewardsByCustomer();
        assertEquals(2, output.size());
    }

    @Test
    public void testRewardsByCustomerTotals() {
        var output = rewardsCalculatorService.calculateRewardsByCustomer();
        assertEquals(2720, output.get("1"));
        assertEquals(2795, output.get("2"));
    }
}