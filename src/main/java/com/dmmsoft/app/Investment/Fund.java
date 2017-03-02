package com.dmmsoft.app.Investment;

import java.util.List;

/**
 * Created by Milo4321 on 2017-02-09.
 */

public class Fund extends Investment {
    public Fund(int id, String name, List<Quotation> quotations) {
        super(id, name, quotations);
    }
}
