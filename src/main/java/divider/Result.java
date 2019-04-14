package divider;

import java.util.Objects;

public class Result {

    public Integer getQuotient() {
        return quotient;
    }

    public Integer getRemainder() {
        return remainder;
    }

    private final Integer quotient;
    private final Integer remainder;

    public Result(Integer quotient, Integer remainder) {
        this.quotient = quotient;
        this.remainder = remainder;
    }

    @Override
    public String toString() {
        return "Result{" +
                "quotient=" + quotient +
                ", remainder=" + remainder +
                '}';
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