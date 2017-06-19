package com.mycompany.marketsurveys.entities;

import com.mycompany.marketsurveys.enums.Sex;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MarketSurvey {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private final Long id;

    @ManyToOne
    @Column(nullable=false)
    private final Subject subject;

    @ManyToOne
    @Column(nullable=false)
    private final Provider provider;

    @Column
    private final String country;

    @Column(nullable=false)
    private final String description;

    @Column(nullable=false)
    private final Date creationDate;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private final Sex sex;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="from",
                    column=@Column(name="age_from")),
            @AttributeOverride(name="to",
                    column=@Column(name="age_to"))
    })
    private final Range ageRange;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",
                    column=@Column(name="income_currency")),
            @AttributeOverride(name="from",
                    column=@Column(name="income_from")),
            @AttributeOverride(name="to",
                    column=@Column(name="income_to"))
    })
    private final CurrencyRange incomeRange;


    public MarketSurvey(Subject subject, Provider provider, String country, String description,
                        Date creationDate, Sex sex,
                        Range ageRange, CurrencyRange incomeRange) {
        id = null;
        this.subject = subject;
        this.provider = provider;
        this.country = country;
        this.description = description;
        this.creationDate = creationDate;
        this.sex = sex;
        this.ageRange = ageRange;
        this.incomeRange = incomeRange;
    }

    private MarketSurvey() {
        id = null;
        this.subject = null;
        this.provider = null;
        this.country = null;
        this.description = null;
        this.creationDate = null;
        this.sex = null;
        this.ageRange = null;
        this.incomeRange = null;
    }

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Sex getSex() {
        return sex;
    }

    public Range getAgeRange() {
        return ageRange;
    }

    public CurrencyRange getIncomeRange() {
        return incomeRange;
    }
}
