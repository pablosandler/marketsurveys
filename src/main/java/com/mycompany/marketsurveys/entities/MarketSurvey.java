package com.mycompany.marketsurveys.entities;

import com.mycompany.marketsurveys.enums.Sex;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MarketSurvey {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    @ManyToOne(optional = false)
    private Subject subject;

    @ManyToOne(optional = false)
    private Provider provider;

    @Column
    private String country;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private Date creationDate;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="from",
                    column=@Column(name="age_from")),
            @AttributeOverride(name="to",
                    column=@Column(name="age_to"))
    })
    private Range ageRange;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",
                    column=@Column(name="income_currency")),
            @AttributeOverride(name="from",
                    column=@Column(name="income_from")),
            @AttributeOverride(name="to",
                    column=@Column(name="income_to"))
    })
    private CurrencyRange incomeRange;


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

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(Subject subject) {
        this.subject = new Subject(subject.getId(), subject.getCode(), subject.getName());
    }

    public void setProvider(Provider provider) {
        this.provider = new Provider(provider.getId(), provider.getName());
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = new Date(creationDate.getTime());
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setAgeRange(Range ageRange) {
        this.ageRange = new Range(ageRange.getFrom(), ageRange.getTo());
    }

    public void setIncomeRange(CurrencyRange incomeRange) {
        this.incomeRange = new CurrencyRange(incomeRange.getCurrency(), incomeRange.getFrom(), incomeRange.getTo());
    }
}
