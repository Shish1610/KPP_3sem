package com.epam.shishov.parameters;

import java.util.ArrayList;

public class ParametersList {
    private ArrayList<InputParameters> parameters = new ArrayList<>();

    public ParametersList() {
    }

    public ParametersList(ArrayList<InputParameters> parameters) {
        this.parameters = parameters;
    }

    public ArrayList<InputParameters> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<InputParameters> parameters) {
        this.parameters = parameters;
    }
}