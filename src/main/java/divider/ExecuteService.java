package divider;

public class ExecuteService {


    public Result getResult(String dividend, String divider) {

        Integer divd = Integer.valueOf(dividend);
        Integer divr = Integer.valueOf(divider);

        if(divr == 0)
            return null;

        Integer quot = divd / divr;
        Integer ren = divd % divr;

        return new Result(quot, ren);
    }
}
