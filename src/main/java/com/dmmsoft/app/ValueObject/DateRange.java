package com.dmmsoft.app.ValueObject;

import java.time.LocalDate;

public class DateRange {

    private LocalDate startDate;
    private LocalDate stopDate;

    public DateRange(LocalDate startDate, LocalDate stopDate) {
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }
}
