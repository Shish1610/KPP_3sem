package validator;

import parameters.InputParameters;

public class Validator {

    public boolean isValid(InputParameters inputParameters) {
        Integer devidend;
        Integer devider;
        try {
            devidend = Integer.valueOf(inputParameters.getDividend());
            devider = Integer.valueOf(inputParameters.getDivider());
            if (devider == 0){
                throw new IllegalArgumentException("Invalid input(Divider = 0)");
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
