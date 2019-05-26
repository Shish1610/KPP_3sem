package com.epam.shishov.divider;


import org.junit.Test;
import com.epam.shishov.parameters.InputParameters;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertEquals;

public class ExecuteServiceTest {

    @Autowired
    ExecuteService service;

    @Test
    public void getResult() {
        Result actual = service.getResult(new InputParameters("17", "6"));
        Result expected = new Result(2,5);

        assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionTest() {
        Result expected = service.getResult(new InputParameters("wert", "eyrery"));
    }
}