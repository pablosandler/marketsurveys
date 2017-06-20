package com.mycompany.marketsurveys.entities;

import javax.persistence.Embeddable;

@Embeddable
public class CurrencyRange {

    private final String currency;

    private final Long from;

    private final Long to;

    public CurrencyRange(String currency, Long from, Long to) {
        //todo throw error if currency is null
        //todo throw error if the two are None
        //todo throw error if from is larger than to
        this.currency = currency;
        this.from = from;
        this.to = to;
    }

    private CurrencyRange() {
        this.currency = null;
        this.from = null;
        this.to = null;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }
}
