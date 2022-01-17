package com.charter.rewardsCalculator.Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {
    CsvReader csvReader = new CsvReader();

    @Test
    public void testNonEmptyResponse(){
        assertTrue(csvReader.readTransactionFile().size()>0);
    }
}