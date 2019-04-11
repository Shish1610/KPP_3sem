package divider;


import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ExecuteServiceTest {

    ExecuteService service=new ExecuteService();

    @Test
    public void getResult() {
        Result actual = service.getResult("17", "6");
        Result expected = new Result(2,5);

        assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionTest() {
        Result expected = service.getResult("wert", "eyrery");
    }
}