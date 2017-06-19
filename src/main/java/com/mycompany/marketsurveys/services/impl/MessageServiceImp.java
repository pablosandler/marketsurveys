package com.mycompany.marketsurveys.services.impl;

import com.mycompany.marketsurveys.services.MessageService;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImp implements MessageService {


    static final ReloadableResourceBundleMessageSource message = new ReloadableResourceBundleMessageSource();

    public MessageServiceImp(){
        message.setBasename("classpath:messages/messages");
        message.setDefaultEncoding("UTF-8");
    }

    public String getMessage(String key) {
        return message.getMessage(key,null, new Locale("en", "EN"));
    }

}
