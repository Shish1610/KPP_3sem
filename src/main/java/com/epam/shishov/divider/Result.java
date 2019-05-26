package com.epam.shishov.divider;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "quotient")
    private Integer quotient;

    @Column(name = "remainder")
    private Integer remainder;

    public Result(Integer quotient, Integer remainder) {
        this.quotient = quotient;
        this.remainder = remainder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuotient() {
        return quotient;
    }

    public void setQuotient(Integer quotient) {
        this.quotient = quotient;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

    public Result(Integer id, Integer quotient, Integer remainder) {
        this.id = id;
        this.remainder = remainder;
        this.quotient = quotient;
    }

    public Result() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(quotient, result.quotient) &&
                Objects.equals(remainder, result.remainder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quotient, remainder);
    }
}