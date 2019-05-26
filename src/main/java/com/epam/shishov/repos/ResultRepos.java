package com.epam.shishov.repos;

import com.epam.shishov.divider.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResultRepos extends CrudRepository<Result, Integer> {

}
