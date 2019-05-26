package com.epam.shishov.divider;

import com.epam.shishov.repos.ResultRepos;
import com.epam.shishov.answers.ResultsList;
import com.epam.shishov.cache.CacheService;
import com.epam.shishov.cache.CacheServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.shishov.parameters.InputParameters;
import com.epam.shishov.parameters.ParametersList;
import com.epam.shishov.validator.Validator;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExecuteService {

    private ResultRepos repos;

    @Autowired
    public ExecuteService(ResultRepos repos) {
        this.repos = repos;
    }


    private CacheService cacheService = CacheServiceImpl.getInstance();

    private static final Logger log = Logger.getLogger(ExecuteController.class);

    private Validator validator = new Validator();

    public Result getResult(InputParameters inputParameters) throws NumberFormatException {

        Integer divd = Integer.valueOf(inputParameters.getDividend());
        Integer divr = Integer.valueOf(inputParameters.getDivider());

        if (!validator.isValid(inputParameters))
            throw new IllegalArgumentException("Invalid input(Divider = 0)");

        Result outcome = cacheService.getFromCache(inputParameters);
        if (outcome != null) {
            log.info("Getted result from com.epam.shishov.cache");
            return outcome;
        } else {
            Integer quot = divd / divr;
            Integer ren = divd % divr;
            Result result = new Result(quot, ren);

            result = repos.save(result);
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
        System.out.println("Amount of input com.epam.shishov.parameters:" + parametersList.getParameters().size());
        System.out.println("Amount of invalid input com.epam.shishov.parameters: " +
                parametersList.getParameters().stream().filter(param -> !validator.isValid(param)).count());

        if (!list.isEmpty()) {
            System.out.println("Max of results:" + list.stream().max(Comparator.comparing(Result::getQuotient)).get());
            System.out.println("Min of results:" + list.stream().min(Comparator.comparing(Result::getQuotient)).get());
        }

        Map<Result, Integer> mostPopularResultMap = new HashMap<>();

        answer.getResults().forEach(res -> {
            if (mostPopularResultMap.containsKey(res)) {
                mostPopularResultMap.put(res, mostPopularResultMap.get(res) + 1);
            } else {
                mostPopularResultMap.put(res, 1);
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

