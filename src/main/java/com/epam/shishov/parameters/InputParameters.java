package com.epam.shishov.parameters;

import java.util.Objects;

public class InputParameters {
    private String dividend;
    private String divider;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputParameters that = (InputParameters) o;
        return Objects.equals(dividend, that.dividend) &&
                Objects.equals(divider, that.divider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dividend, divider);
    }

    public InputParameters(String dividend, String divider) {
        this.dividend = dividend;
        this.divider = divider;
    }

    public String getDividend() {
        return dividend;
    }

    public void setDividend(String dividend) {
        this.dividend = dividend;
    }

    public String getDivider() {
        return divider;
    }

    public void setDivider(String divider) {
        this.divider = divider;
    }

    @Override
    public String toString() {
        return "InputParameters{" +
                "dividend='" + dividend + '\'' +
                ", com.epam.shishov.divider='" + divider + '\'' +
                '}';
    }
}
