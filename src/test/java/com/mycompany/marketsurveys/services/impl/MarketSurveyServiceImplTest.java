package com.mycompany.marketsurveys.services.impl;

import com.mycompany.marketsurveys.entities.*;
import com.mycompany.marketsurveys.enums.Sex;
import com.mycompany.marketsurveys.errorHandling.exceptions.BusinessException;
import com.mycompany.marketsurveys.repositories.ClientRepository;
import com.mycompany.marketsurveys.repositories.MarketSurveyRepository;
import com.mycompany.marketsurveys.repositories.ProviderRepository;
import com.mycompany.marketsurveys.repositories.SubjectRepository;
import com.mycompany.marketsurveys.services.MarketSurveyService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class MarketSurveyServiceImplTest {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MarketSurveyRepository marketSurveyRepository;

    @Autowired
    private ClientRepository clientRepository;

    private MarketSurveyService marketSurveyService;

    @Test
    @Ignore
    public void whenRequesterIsNotAValidOneThrowAnException(){

    }

    @Test
    public void whenProviderIsSentFilterByIt(){
        Provider provider1 = providerRepository.save(new Provider(null, "provider"));
        Subject subject1 = subjectRepository.save(new Subject(null, "code", "name"));
        MarketSurvey marketSurvey = new MarketSurvey(subject1, provider1, "ES", "desc", new Date(), Sex.MALE, null, null);
        marketSurveyRepository.save(marketSurvey);

        Provider provider2 = providerRepository.save(new Provider(null, "provider"));
        Subject subject2 = subjectRepository.save(new Subject(null, "code", "name"));
        MarketSurvey marketSurvey2 = new MarketSurvey(subject2, provider2, "ES", "desc", new Date(), Sex.MALE, null, null);
        marketSurveyRepository.save(marketSurvey2);

        Client client = new Client("clientName");
        clientRepository.save(client);

        try {
            marketSurveyService = new MarketSurveyServiceImpl(clientRepository, marketSurveyRepository);
            List<MarketSurvey> marketSurveys = marketSurveyService.getAvailableSurveys(2L, provider2.getId(), null, null, null, null, null, null, null, null);
            assertTrue(marketSurveys.size()==1);
            assertEquals(marketSurveys.get(0), marketSurvey2);

        } catch (BusinessException e) {
            fail();
        }
    }

    @Test
    public void whenNoProviderIsSentGetAllObjects(){
        Provider provider1 = providerRepository.save(new Provider(null, "provider"));
        Subject subject1 = subjectRepository.save(new Subject(null, "code", "name"));
        MarketSurvey marketSurvey = new MarketSurvey(subject1, provider1, "ES", "desc", new Date(), Sex.MALE, null, null);
        marketSurveyRepository.save(marketSurvey);

        Provider provider2 = providerRepository.save(new Provider(null, "provider"));
        Subject subject2 = subjectRepository.save(new Subject(null, "code", "name"));
        MarketSurvey marketSurvey2 = new MarketSurvey(subject2, provider2, "ES", "desc", new Date(), Sex.MALE, null, null);
        marketSurveyRepository.save(marketSurvey2);

        Client client = new Client("clientName");
        clientRepository.save(client);

        try {
            marketSurveyService = new MarketSurveyServiceImpl(clientRepository, marketSurveyRepository);
            List<MarketSurvey> marketSurveys = marketSurveyService.getAvailableSurveys(1L, null, null, null, null, null, null, null, null, null);
            assertTrue(marketSurveys.size()==2L);
            assertArrayEquals(marketSurveys.toArray(), new MarketSurvey[]{marketSurvey, marketSurvey2});

        } catch (BusinessException e) {
            fail();
        }
    }

}