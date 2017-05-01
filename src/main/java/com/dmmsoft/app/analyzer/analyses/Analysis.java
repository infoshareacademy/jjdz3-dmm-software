package com.dmmsoft.app.analyzer.analyses;


import com.dmmsoft.app.analyzer.suggester.InputSuggester;
import com.dmmsoft.app.pojo.MainContainer;

/**
 * Created by milo on 01.05.17.
 */
public abstract class Analysis {

    protected MainContainer mainContainer;
    protected InputSuggester suggester = new InputSuggester();

    public MainContainer getMainContainer() {
        return mainContainer;
    }
    public void setMainContainer(MainContainer mainContainer) {
        this.mainContainer = mainContainer;
    }

}
