package divider;

import counter.CounterService;
import counter.CounterServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parameters.InputParameters;
import parameters.ParametersList;


@RestController
public class ExecuteController {

    private CounterService counterService = CounterServiceImpl.getInstance();

    private static final Logger log = Logger.getLogger(ExecuteController.class);

    private ExecuteService service = new ExecuteService();

    @GetMapping("/division")
    public ResponseEntity division(@RequestParam(value = "dividend") String dividend,
                                   @RequestParam(value = "divider") String divider) {
        try {
            Result result = service.getResult(new InputParameters(dividend, divider));
            if (result == null) {
                log.info("Dividend = " + dividend + ", Divider = " + divider +
                        ". Incorrect parameters");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect parameters");
            }
            log.info("Dividend = " + dividend + ", Divider = " + divider +
                    ". HTTP status 200, response :" + result.toString());

            return ResponseEntity.ok(result);
        } catch (NumberFormatException exception) {
            log.info("Dividend = " + dividend + ", Divider = " + divider +
                    ". Bad parameters");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad parameters");
        } catch (IllegalArgumentException exception) {
            log.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }


    }
    @PostMapping("/division")
    public ResponseEntity getSolutions(@RequestBody ParametersList parametersList) {
        try {
            return ResponseEntity.ok(service.getResult(parametersList));
        } catch (NumberFormatException exception) {
            log.info("Bad parameters (not a number)");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad parameters (not a number)");
        } catch (IllegalArgumentException exception) {
            log.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
