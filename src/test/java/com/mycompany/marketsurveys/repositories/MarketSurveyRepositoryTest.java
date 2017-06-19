package com.mycompany.marketsurveys.repositories;

import com.mycompany.marketsurveys.entities.*;
import com.mycompany.marketsurveys.enums.Sex;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class MarketSurveyRepositoryTest extends TestCase {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MarketSurveyRepository repository;

    /*@Autowired
    private TestEntityManager entityManager;*/

    @Test
    public void xxx(){
        Provider provider = providerRepository.save(new Provider("provider"));
        Subject subject = subjectRepository.save(new Subject("code", "name"));

        Range ageRange = new Range(3L, 4L);
        CurrencyRange incomeRange = new CurrencyRange("EUR", 1L, 2L);

        MarketSurvey marketSurvey = new MarketSurvey(subject, provider, "ES", "desc", new Date(), Sex.MALE, ageRange, incomeRange);
        repository.save(marketSurvey);

        List<MarketSurvey> aa = repository.find("code", 1L, Sex.MALE, "ES", 3l, 4l, 1L, 2L, "EUR");

        assertTrue(false==aa.isEmpty());
    }

}