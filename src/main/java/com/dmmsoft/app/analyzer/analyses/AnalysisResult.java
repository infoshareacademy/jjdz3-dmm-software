package com.dmmsoft.app.analyzer.analyses;

/**
 * Created by milo on 30.04.17.
 */
public abstract class AnalysisResult {

    protected String returnMessage;

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
