package com.mycompany.marketsurveys.controllers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mycompany.marketsurveys.entities.MarketSurvey;
import com.mycompany.marketsurveys.entities.Provider;
import com.mycompany.marketsurveys.entities.Subject;
import com.mycompany.marketsurveys.enums.Sex;
import com.mycompany.marketsurveys.errorHandling.GlobalHandler;
import com.mycompany.marketsurveys.serviceResponse.ServiceResponse;
import com.mycompany.marketsurveys.services.MarketSurveyService;
import com.mycompany.marketsurveys.services.MessageService;
import com.mycompany.marketsurveys.validations.MarketSurveyValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MarketSurveyController.class)
public class MarketSurveyControllerTest {

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @MockBean
    private MessageService messageService;
    @MockBean
    private MarketSurveyValidator marketSurveyValidator;
    @MockBean
    private MarketSurveyService marketSurveyService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MarketSurveyController(marketSurveyValidator, marketSurveyService))
                .setControllerAdvice(new GlobalHandler(messageService)).build();
    }

    @Test
    public void whenValidationFailsReturnErrorMessage() throws Exception {
        when(marketSurveyValidator.validateCountry(null)).thenReturn(Optional.of("error.country.not.found"));
        when(messageService.getMessage("error.country.not.found")).thenReturn("error with country");

        MvcResult result = this.mockMvc.perform(
                get("/market-survey")
                    .param("requester", "1")
                    .param("provider","1")
                    .param("sex","MALE")
                    .param("subject","ES").accept(contentType)
        ).andExpect(status().isBadRequest()).andReturn();
        String content = result.getResponse().getContentAsString();

        ServiceResponse obj = new Gson().fromJson(content, ServiceResponse.class);
        assertEquals("error with country", obj.getMessages().get(0));
    }

    @Test
    public void whenNoErrorsAreFoundReturnAListOfMarketSurveys() throws Exception {
        MarketSurvey marketSurveys = new MarketSurvey(new Subject("code", "name"), new Provider("provider"), "ES", "desc", new Date(), Sex.MALE, null, null);
        List<MarketSurvey> list = new ArrayList<>();
        list.add(marketSurveys);
        when(marketSurveyService.getAvailableSurveys(1L, 1L, "ES", null, Sex.MALE, null, null, null, null, null)).thenReturn(list);

        when(marketSurveyValidator.validateCountry(null)).thenReturn(Optional.empty());

        MvcResult result = this.mockMvc.perform(
                get("/market-survey")
                        .param("requester", "1")
                        .param("provider","1")
                        .param("sex","MALE")
                        .param("subject","ES").accept(contentType)
        ).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        ServiceResponse serviceResponse = new Gson().fromJson(content, ServiceResponse.class);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class,
                new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                });

        List<MarketSurvey> obj = gsonBuilder.create().fromJson(serviceResponse.getData().toString(), new TypeToken<List<MarketSurvey>>(){}.getType());
        assertTrue(obj.size()==1);
        assertNotNull(obj.get(0));
    }


}