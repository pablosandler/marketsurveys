package com.mycompany.marketsurveys.errorHandling;

import com.mycompany.marketsurveys.errorHandling.exceptions.ValidationException;
import com.mycompany.marketsurveys.serviceResponse.ServiceResponse;
import com.mycompany.marketsurveys.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

    private MessageService messageService;

    @Autowired
    public GlobalHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ServiceResponse handleBaseException(Exception e){
        logger.error(e.getMessage(), e.getStackTrace());
        return new ServiceResponse(messageService.getMessage("error.unexpected"));
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ServiceResponse> handleException(ValidationException exception) {
        ServiceResponse serviceResponse = new ServiceResponse();

        exception.getMessages().forEach(error ->
                serviceResponse.addMessage(messageService.getMessage(error)));

        logger.info("Input data errors", serviceResponse.getMessages());
        return new ResponseEntity<>(serviceResponse, HttpStatus.BAD_REQUEST);
    }

}
