package com.mycompany.marketsurveys.validations;

import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MarketSurveyValidatorTest {

    @Test
    public void whenValidatingAgeRangeReturnEmptySetIfNoErrorsArise() {
        MarketSurveyValidator validator = new MarketSurveyValidator();

        Set<String> result = validator.validateAgeRange(null, null);

        assertTrue(result.isEmpty());
    }

    @Test
    public void whenValidatingAgeRangeReturnAllErrorMessages() {
        MarketSurveyValidator validator = new MarketSurveyValidator();

        Set<String> result = validator.validateAgeRange(-10L, -20L);

        assertTrue(result.size()==3);
    }

    @Test
    public void whenValidatingCountryReturnNothingIfCountryIsNotPresent() {
        MarketSurveyValidator validator = new MarketSurveyValidator();

        Optional<String> result = validator.validateCountry(null);

        assertFalse(result.isPresent());
    }

    @Test
    public void whenValidatingCountryReturnNothingIfCountryExists() {
        MarketSurveyValidator validator = new MarketSurveyValidator();

        Optional<String> result = validator.validateCountry("es");

        assertFalse(result.isPresent());
    }

    @Test
      public void whenValidatingCountryReturnErrorIfCountryDoesNotExist() {
        MarketSurveyValidator validator = new MarketSurveyValidator();

        Optional<String> result = validator.validateCountry("eeeeeee");

        assertTrue(result.isPresent());
    }

    @Test
    public void whenValidatingIncomeReturnAnErrorIfFromOrToArePresentAndCurrencyIsNot() {
        MarketSurveyValidator validator = new MarketSurveyValidator();

        Set<String> result = validator.validateIncome(null, 1L, null);
        assertFalse(result.isEmpty());

        Set<String> result2 = validator.validateIncome(null, null, 1L);
        assertFalse(result2.isEmpty());

        Set<String> result3 = validator.validateIncome(null, 1L, 1L);
        assertFalse(result3.isEmpty());
    }

    @Test
    public void whenValidatingIncomeReturnAnErrorIfCurrencyIsNotFound() {
        MarketSurveyValidator validator = new MarketSurveyValidator();
        Set<String> result = validator.validateIncome("yyy", null, null);
        assertFalse(result.isEmpty());
    }

}