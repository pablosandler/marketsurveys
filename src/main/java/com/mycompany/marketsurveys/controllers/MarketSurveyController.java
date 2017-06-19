package com.mycompany.marketsurveys.controllers;

import com.mycompany.marketsurveys.entities.MarketSurvey;
import com.mycompany.marketsurveys.enums.Sex;
import com.mycompany.marketsurveys.errorHandling.exceptions.ValidationException;
import com.mycompany.marketsurveys.serviceResponse.ServiceResponse;
import com.mycompany.marketsurveys.services.MarketSurveyService;
import com.mycompany.marketsurveys.validations.MarketSurveyValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/market-survey")
public class MarketSurveyController {

    private static Logger logger = Logger.getLogger(MarketSurveyController.class);

    private MarketSurveyValidator marketSurveyValidator;
    private MarketSurveyService marketSurveyService;

    @Autowired
    public MarketSurveyController(MarketSurveyValidator marketSurveyValidator, MarketSurveyService marketSurveyService){
        this.marketSurveyValidator = marketSurveyValidator;
        this.marketSurveyService = marketSurveyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ServiceResponse> getAvailableMarketSurveys(
            @RequestParam("requester") Long requester,
            @RequestParam("provider") Long provider,
            @RequestParam("subject") String subject,
            @RequestParam(name="country", required = false) String country,
            @RequestParam(name="sex", required = false) Sex sex,
            @RequestParam(name="age_from", required = false) Long ageFrom,
            @RequestParam(name="age_to", required = false) Long ageTo,
            @RequestParam(name="income_currency", required = false) String incomeCurrency,
            @RequestParam(name="income_currency", required = false) Long incomeFrom,
            @RequestParam(name="income_currency", required = false) Long incomeTo) {

        Set<String> errors = new HashSet<>();

        errors.addAll(marketSurveyValidator.validateAgeRange(ageFrom, ageTo));

        errors.addAll(marketSurveyValidator.validateIncome(incomeCurrency, incomeFrom, incomeTo));

        Optional<String> countryValidationResult = marketSurveyValidator.validateCountry(country);
        if(countryValidationResult.isPresent()) {
            errors.add(countryValidationResult.get());
        }

        if(!errors.isEmpty()){
            logger.info("Validation errors found");
            throw new ValidationException(errors);
        }

        List<MarketSurvey> availableMarketSurveys = marketSurveyService.getAvailableSurveys(requester, provider, subject, country,
                sex, ageFrom, ageTo, incomeCurrency, incomeFrom, incomeTo);

        ServiceResponse response = new ServiceResponse();
        response.setData(new ArrayList<>(availableMarketSurveys));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
