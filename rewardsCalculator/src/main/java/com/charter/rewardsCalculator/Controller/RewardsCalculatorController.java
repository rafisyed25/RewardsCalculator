package com.charter.rewardsCalculator.Controller;

import com.charter.rewardsCalculator.Service.RewardsCalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.Map;

@RestController
public class RewardsCalculatorController {

    private final RewardsCalculatorService rewardsCalculatorService;

    public RewardsCalculatorController(RewardsCalculatorService rewardsCalculatorService) {
        this.rewardsCalculatorService = rewardsCalculatorService;
    }

    @RequestMapping(value = "/calculateRewardsByCustomer", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Integer>> calculateRewardsByCustomer() {
        var rewardsByCustomer = rewardsCalculatorService.calculateRewardsByCustomer();
        return new ResponseEntity<>(rewardsByCustomer, HttpStatus.OK);
    }

    @RequestMapping(value = "/calculateRewardsByMonth", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Map<Month,Integer>>> calculateRewardsByMonth() {
        var rewardsByMonth = rewardsCalculatorService.calculateRewardsByMonth();
        return new ResponseEntity<>(rewardsByMonth, HttpStatus.OK);
    }

}
