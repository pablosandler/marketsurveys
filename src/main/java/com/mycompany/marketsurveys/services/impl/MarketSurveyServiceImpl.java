package com.mycompany.marketsurveys.services.impl;

import com.mycompany.marketsurveys.entities.MarketSurvey;
import com.mycompany.marketsurveys.enums.Sex;
import com.mycompany.marketsurveys.services.MarketSurveyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketSurveyServiceImpl implements MarketSurveyService{
    @Override
    public List<MarketSurvey> getAvailableSurveys(Long requester, Long provider, String subject, String country,
                                                  Sex sex, Long ageFrom, Long ageTo, String incomeCurrency, Long incomeFrom, Long incomeTo) {

        //todo look for requester, throw an exception if not found
        //todo look market surveys with received parameters
        return null;
    }
}
