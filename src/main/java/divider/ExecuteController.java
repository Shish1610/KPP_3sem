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

    @GetMapping("/getResult")
    public ResponseEntity getResult(@RequestParam(value = "dividend") String dividend,
                                   @RequestParam(value = "divider") String divider) {
        counterService.increment();
        log.info("counter of requests on server:" + counterService.getCounter().toString());
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
    @PostMapping("/getResults")
    public ResponseEntity getResults(@RequestBody ParametersList parametersList) {
        try {
            return ResponseEntity.ok(service.getResults(parametersList));
        } catch (NumberFormatException exception) {
            log.info("Bad parameters");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad parameters");
        } catch (IllegalArgumentException exception) {
            log.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
