package com.dmmsoft.app.analyzer.analyses.indicator;

import com.dmmsoft.app.analyzer.analyses.AnalysisCriteria;

import javax.persistence.MappedSuperclass;

/**
 * Created by milo on 14.04.17.
 */
@MappedSuperclass
public class IndicatorCriteria extends AnalysisCriteria {
    private String name;

    public String getName() {
        return name;
    }

    public IndicatorCriteria(String name) {
        this.name = name;
    }

    public IndicatorCriteria() {
    }
}
