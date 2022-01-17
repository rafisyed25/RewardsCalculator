package com.charter.rewardsCalculator.Service;

import com.charter.rewardsCalculator.model.Transaction;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvReader {
        private final Logger logger = LoggerFactory.getLogger(CsvReader.class);
        private List<Transaction> transactionList = new ArrayList<Transaction>();

        public List<Transaction> readTransactionFile(){
                try {
                        var file = ResourceUtils.getFile("classpath:Transaction.csv");
                        var fileReader =  new FileReader(file);
                        var reader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
                        var fileContent = reader.readAll();
                        var datePattern = "MM-dd-yyyy";
                        var dateFormatter = DateTimeFormatter.ofPattern(datePattern);
                        fileContent.forEach(record -> {
                                transactionList.add(new Transaction(record[0], Integer.valueOf(record[1]), LocalDate.parse(record[2], dateFormatter)));
                        });

                } catch (FileNotFoundException e) {
                        logger.error("error loading file from classpath", e);
                } catch (IOException e) {
                        logger.error("I/O error while reading csv", e);
                } catch (CsvException e) {
                        logger.error("Csv Exception error while reading csv", e);
                }
                return transactionList;
        }
}
