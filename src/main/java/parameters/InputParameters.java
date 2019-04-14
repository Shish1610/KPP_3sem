package parameters;

public class InputParameters {
    private String dividend;
    private String divider;

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
                ", divider='" + divider + '\'' +
                '}';
    }
}
