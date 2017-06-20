package com.mycompany.marketsurveys.entities;

import javax.persistence.*;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    private String name;

    public Provider(Long id, String name) {
        this.id=id;
        this.name = name;
    }

    private Provider() {
        id=null;
        name=null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
