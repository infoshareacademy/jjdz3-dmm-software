package com.dmmsoft.app.analyzer.analyses;

/**
 * Created by milo on 30.04.17.
 */
public abstract class AnalysisResult {

    protected String returnMessage;
    protected AnalysisInput finallyEvaluatedInput;

    public AnalysisInput getFinallyEvaluatedInput() {
        return finallyEvaluatedInput;
    }

    public void setFinallyEvaluatedInput(AnalysisInput finallyEvaluatedInput) {
        this.finallyEvaluatedInput = finallyEvaluatedInput;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
