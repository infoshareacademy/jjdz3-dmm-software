package com.dmmsoft.app.pojo;

import java.util.List;

/**
 * Created by Milo4321 on 2017-02-09.
 */
public class Currency extends Investment {

    public Currency(int Id, String name, List<Quotation> quotations) {
        super(Id, name, quotations);
    }

}

