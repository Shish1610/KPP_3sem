package com.epam.shishov.cache;

import com.epam.shishov.parameters.InputParameters;
import com.epam.shishov.divider.Result;

public interface CacheService {
    void add(InputParameters inputParameters, Result result);
    Result getFromCache(InputParameters inputParameters);
}