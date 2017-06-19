package com.mycompany.marketsurveys.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Range{

    private Long from;

    private Long to;

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

}
