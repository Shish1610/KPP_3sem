package divider;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ExecuteController {

    private static final Logger log = Logger.getLogger(ExecuteController.class);

    private ExecuteService service = new ExecuteService();

    @RequestMapping("/division")
    public ResponseEntity division(@RequestParam(value = "dividend") String dividend,
                                   @RequestParam(value = "divider") String divider) {
        try {
            Result result = service.getResult(dividend, divider);
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
        }

    }

}
