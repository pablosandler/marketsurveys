package com.mycompany.marketsurveys.services.impl;

import com.mycompany.marketsurveys.entities.Client;
import com.mycompany.marketsurveys.entities.MarketSurvey;
import com.mycompany.marketsurveys.entities.Provider;
import com.mycompany.marketsurveys.enums.Sex;
import com.mycompany.marketsurveys.errorHandling.exceptions.BusinessException;
import com.mycompany.marketsurveys.repositories.ClientRepository;
import com.mycompany.marketsurveys.repositories.MarketSurveyRepository;
import com.mycompany.marketsurveys.services.MarketSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketSurveyServiceImpl implements MarketSurveyService{

    private static final String ERROR_CLIENT_NOT_FOUND = "error.client.not.found";

    private ClientRepository clientRepository;
    private MarketSurveyRepository marketSurveyRepository;

    @Autowired
    public MarketSurveyServiceImpl(ClientRepository clientRepository, MarketSurveyRepository marketSurveyRepository){
        this.clientRepository = clientRepository;
        this.marketSurveyRepository = marketSurveyRepository;
    }

    @Override
    public List<MarketSurvey> getAvailableSurveys(Long requester, Long provider,
                                                  String subject, String country, Sex sex,
                                                  Long ageFrom, Long ageTo,
                                                  String incomeCurrency, Long incomeFrom, Long incomeTo) throws BusinessException {//todo change exception to businessException

        Optional<Client> client = clientRepository.findById(requester);
        if(!client.isPresent()){
            throw new BusinessException(ERROR_CLIENT_NOT_FOUND);
        }
        //todo link between providers and subscriptions and clients?

        MarketSurvey marketSurveyProbe = new MarketSurvey(null, null, null, null, null, null, null, null);

        Optional<Long> providerOpt = Optional.ofNullable(provider);
        if(providerOpt.isPresent()){
            marketSurveyProbe.setProvider(new Provider(provider, null));
        }

        /*Optional<String> subjectOpt = Optional.ofNullable(subject);
        if(subjectOpt.isPresent()){
            marketSurveyProbe.setSubject(new Subject(null, subject, null));
        }*/

        //todo look for requester, throw an exception if not found
        //todo look market surveys with received parameters
        return marketSurveyRepository.findAll(Example.of(marketSurveyProbe));
    }
}
