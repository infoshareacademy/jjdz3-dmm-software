package com.dmmsoft.app.analyzer.analyses;


import com.dmmsoft.app.analyzer.suggester.InputCriteriaSuggester;
import com.dmmsoft.app.pojo.MainContainer;

/**
 * Created by milo on 01.05.17.
 */
public abstract class Analysis {

    protected MainContainer mainContainer;
    protected InputCriteriaSuggester suggester = new InputCriteriaSuggester();

}
