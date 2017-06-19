package com.mycompany.marketsurveys.validations;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ValidationUtilsTest {

    @Test
    public void whenCheckingIfOneNumberIsLargerThanAValueReturnNothingIfNumberIsNotPresent() {
        Optional<String> response = ValidationUtils.checkLargerThan(Optional.empty(), 10L, "message");
        assertFalse(response.isPresent());
    }

    @Test
    public void whenCheckingIfOneNumberIsLargerThanAValueReturnErrorIfNumberIsLowerThanValue() {
        Optional<String> response = ValidationUtils.checkLargerThan(Optional.of(2L), 10L, "message");
        assertTrue(response.isPresent());
        assertEquals("message", response.get());
    }

    @Test
    public void whenCheckingIfOneNumberIsLargerThanAValueReturnNothingIfNumberIsLarger() {
        Optional<String> response = ValidationUtils.checkLargerThan(Optional.of(12L), 10L, "message");
        assertFalse(response.isPresent());
    }

    @Test
    public void whenCheckingRangeReturnNothingIfBothNumbersAreNotPresent() {
        Optional<String> response = ValidationUtils.checkRange(Optional.empty(), Optional.empty(), "message");
        assertFalse(response.isPresent());

        Optional<String> response2 = ValidationUtils.checkRange(Optional.of(1L), Optional.empty(), "message");
        assertFalse(response2.isPresent());

        Optional<String> response3 = ValidationUtils.checkRange(Optional.empty(), Optional.of(1L), "message");
        assertFalse(response3.isPresent());
    }

    @Test
    public void whenCheckingRangeReturnNothingIfFirstParamIsLowerThanSecond() {
        Optional<String> response = ValidationUtils.checkRange(Optional.of(1L), Optional.of(2L), "message");
        assertFalse(response.isPresent());
    }

    @Test
    public void whenCheckingRangeReturnMessageIfFirstParamIsLargerThanSecond() {
        Optional<String> response = ValidationUtils.checkRange(Optional.of(2L), Optional.of(1L), "message");
        assertTrue(response.isPresent());
        assertEquals("message", response.get());
    }

}