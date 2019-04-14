package divider;

import answers.ResultsList;
import cache.CacheService;
import cache.CacheServiceImpl;
import org.springframework.stereotype.Service;
import parameters.InputParameters;
import parameters.ParametersList;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ExecuteService {

    private CacheService cacheService = CacheServiceImpl.getInstance();


    public Result getResult(InputParameters inputParameters) throws NumberFormatException{

        Integer divd = Integer.valueOf(inputParameters.getDividend());
        Integer divr = Integer.valueOf(inputParameters.getDivider());

        if (divr == 0)
            throw new IllegalArgumentException("Invalid input(Divider = 0)");

        Result outcome = cacheService.getFromCache(inputParameters);
        if (outcome != null) {
            return outcome;
        } else {
            Integer quot = divd / divr;
            Integer ren = divd % divr;
            Result result = new Result(quot, ren);
            cacheService.add(inputParameters, result);
            return result;
        }


    }
    public ResultsList getResult(ParametersList parametersList) {
        List<Result> list = parametersList.getParameters().stream()
                .map(this::getResult)
                .collect(Collectors.toList());

        return new ResultsList(new ArrayList<>(list));

    }
}
