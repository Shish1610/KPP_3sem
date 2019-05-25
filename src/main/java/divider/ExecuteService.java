package divider;

import answers.ResultsList;
import cache.CacheService;
import cache.CacheServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import parameters.InputParameters;
import parameters.ParametersList;
import validator.Validator;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExecuteService {

    private CacheService cacheService = CacheServiceImpl.getInstance();

    private static final Logger log = Logger.getLogger(ExecuteController.class);

    private Validator validator = new Validator();

    public Result getResult(InputParameters inputParameters) throws NumberFormatException {

        Integer divd = Integer.valueOf(inputParameters.getDividend());
        Integer divr = Integer.valueOf(inputParameters.getDivider());

        if (divr == 0)
            throw new IllegalArgumentException("Invalid input(Divider = 0)");

        Result outcome = cacheService.getFromCache(inputParameters);
        if (outcome != null) {
            log.info("Getted result from cache");
            return outcome;
        } else {
            Integer quot = divd / divr;
            Integer ren = divd % divr;
            Result result = new Result(quot, ren);
            cacheService.add(inputParameters, result);
            return result;
        }

    }


    public ResultsList getResults(ParametersList parametersList) {
        List<Result> list = parametersList.getParameters().stream()
                .filter(p -> validator.isValid(p))
                .map(this::getResult)
                .collect(Collectors.toList());
        System.out.println("List size: " + list.size());

        ResultsList answer = new ResultsList(new ArrayList<>(list));
        System.out.println("Amount of input parameters:" + parametersList.getParameters().size());
        System.out.println("Amount of invalid input parameters: " +
                parametersList.getParameters().stream().filter(param -> !validator.isValid(param)).count());

        if (!list.isEmpty()) {
            System.out.println("Max of results:" + list.stream().max(Comparator.comparing(Result::getQuotient)).get());
            System.out.println("Min of results:" + list.stream().min(Comparator.comparing(Result::getQuotient)).get());
        }

        Map<Result, Integer> mostPopularResultMap = new HashMap<>();

        answer.getResults().forEach(fibonacci -> {
            if (mostPopularResultMap.containsKey(fibonacci)) {
                mostPopularResultMap.put(fibonacci, mostPopularResultMap.get(fibonacci) + 1);
            } else {
                mostPopularResultMap.put(fibonacci, 1);
            }
        });

        int maxNumber = mostPopularResultMap.values().stream()
                .max(Integer::compareTo)
                .orElse(1);

        List<Result> popular = new ArrayList<>();

        mostPopularResultMap.forEach((k, v) -> {
            if(v == maxNumber) {
                popular.add(k);
            }
        });

        System.out.println("Most popular result:" + popular);

        return answer;

    }
}

