package com.epam.shishov.cache;

import com.epam.shishov.divider.ExecuteController;
import com.epam.shishov.parameters.InputParameters;
import com.epam.shishov.divider.Result;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

public class CacheServiceImpl implements CacheService {

    private static final Logger log = Logger.getLogger(ExecuteController.class);

    public static final Integer MAX_CACHE_RECORDS = 20;

    private ConcurrentHashMap<InputParameters, Result> cacheMap = new ConcurrentHashMap<>();

    private static volatile CacheServiceImpl instance = null;

    private CacheServiceImpl() {
    }

    public static CacheServiceImpl getInstance() {
        CacheServiceImpl localInstance = instance;

        if (instance == null) {
            synchronized (CacheServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CacheServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void add(InputParameters inputParameters, Result result) {
        if(cacheMap.size() >= MAX_CACHE_RECORDS){
            cacheMap.clear();
            log.info("Cleared com.epam.shishov.cache");
        }
        cacheMap.put(inputParameters, result);
        log.info("Added result to com.epam.shishov.cache");
    }

    @Override
    public Result getFromCache(InputParameters inputParameters) {
        return cacheMap.get(inputParameters);
    }
}