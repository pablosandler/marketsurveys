package com.mycompany.marketsurveys.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Optional;

@Embeddable
public class CurrencyRange {

    private final String currency;

    private Long from;

    private Long to;

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
}
