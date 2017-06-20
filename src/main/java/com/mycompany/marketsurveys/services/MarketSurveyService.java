package com.mycompany.marketsurveys.services;

import com.mycompany.marketsurveys.entities.MarketSurvey;
import com.mycompany.marketsurveys.enums.Sex;
import com.mycompany.marketsurveys.errorHandling.exceptions.BusinessException;

import java.util.List;

public interface MarketSurveyService {

    List<MarketSurvey> getAvailableSurveys(Long requester, Long provider, String subject,
                                           String country, Sex sex, Long ageFrom, Long ageTo,
                                           String incomeCurrency, Long incomeFrom, Long incomeTo) throws BusinessException;
}
