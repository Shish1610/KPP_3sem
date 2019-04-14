package answers;

import divider.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class ResultsList {
    private ArrayList<Result> results = new ArrayList<>();

    public ResultsList(ArrayList<Result> results) {
        this.results = results;
    }

    public ResultsList() {

    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResultsList{" +
                "results=" + results +
                '}';
    }
}