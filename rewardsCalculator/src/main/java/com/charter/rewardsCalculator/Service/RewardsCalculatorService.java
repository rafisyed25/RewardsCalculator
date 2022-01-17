package com.charter.rewardsCalculator.Service;

import com.charter.rewardsCalculator.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardsCalculatorService {

    private final CsvReader csvReader;

    public RewardsCalculatorService(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public Map<String, Map<Month, Integer>> calculateRewardsByMonth() {
        Map rewardByMonth = new HashMap<String,Map<Month, Integer>>();
        var transactionList = csvReader.readTransactionFile();
        var groupByCustomer = transactionList.stream().collect(Collectors.groupingBy(transaction -> transaction.getCustomerId()));
        var groupByCustomerAndMonth = new HashMap<String, Map<Month, List<Transaction>>>();
        groupByCustomer.entrySet().forEach(value -> {
            var groupByMonth = value.getValue().stream().collect(Collectors.groupingBy(transaction -> transaction.getDate().getMonth()));
            groupByCustomerAndMonth.put(value.getKey(), groupByMonth);
        });
        groupByCustomerAndMonth.entrySet().forEach(customerAndMonthMap -> {
            var monthMap = new HashMap<Month, Integer>();
            customerAndMonthMap.getValue().entrySet().forEach(groupByMonthMap -> {
                var rewardsTotal = calculateRewards(groupByMonthMap.getValue());
                monthMap.put(groupByMonthMap.getKey(),rewardsTotal);
            });
            rewardByMonth.put(customerAndMonthMap.getKey(), monthMap);
        });
        return rewardByMonth;
    }

    public Map<String, Integer> calculateRewardsByCustomer(){
        Map rewardByMonth = new HashMap<String,Map<Month, Integer>>();
        var transactionList = csvReader.readTransactionFile();
        var groupByCustomer = transactionList.stream().collect(Collectors.groupingBy(transaction -> transaction.getCustomerId()));
        groupByCustomer.entrySet().forEach(value -> {
            rewardByMonth.put(value.getKey(), calculateRewards(value.getValue()));
        });
        return rewardByMonth;
    }

    private Integer calculateRewards(List<Transaction> values) {
        var output = values.stream().map(value -> {
            var rewardPoints = 0;
            var transactionValue = value.getTransactionValue();
            var isHundred = transactionValue-100;
            if(isHundred > 0){
                rewardPoints = (isHundred*2)+50;
            }
            else if(transactionValue > 50){
                rewardPoints = transactionValue-50;
            }
            return rewardPoints;
        });
        var result = output.collect(Collectors.summingInt(Integer::intValue));
        return result;
    }

}
