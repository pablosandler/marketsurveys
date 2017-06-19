package com.mycompany.marketsurveys.serviceResponse;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse {

    private List<Object> data;

    private List<String> messages;

    public ServiceResponse(){
        this.data = new ArrayList<>();
        this.messages = new ArrayList<String>();
    }

    public ServiceResponse(Object data){
        this();
        this.addData(data);
    }

    public ServiceResponse(String message){
        this();
        addMessage(message);
    }

    public void addMessage(String message){
        Assert.hasText(message);//todo change this
        this.messages.add(message);
    }

    public void addData(Object data){
        Assert.notNull(data);//todo change this
        this.data.add(data);
    }

    public void setData(List<Object> data){
        Assert.notNull(data);//todo change this
        this.data = new ArrayList<>(data);
    }

    public List<Object> getData() {
        return new ArrayList<>(data);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

}
