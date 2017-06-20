package com.mycompany.marketsurveys.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Range{

    private final Long from;

    private final Long to;

    public Range(Long from, Long to) {
        //todo throw error if the two are None
        //todo throw error if from is larger than to
        this.from = from;
        this.to = to;
    }

    private Range() {
        this.from = null;
        this.to = null;
    }

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }
}
