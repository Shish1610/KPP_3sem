package cache;

import parameters.InputParameters;
import divider.Result;

public interface CacheService {
    void add(InputParameters inputParameters, Result result);
    Result getFromCache(InputParameters inputParameters);
}