package com.unipi.stavrosvl7.faceit.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dom_country")
public class Country implements Serializable {

    @Id
    @GeneratedValue(generator = "dom_country_id_seq")
    @SequenceGenerator(name = "dom_country_id_seq", sequenceName = "dom_country_id_seq", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private String phoneInternationalPrefix;

    public Country(Long id,
                   String name,
                   String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Country() {
    }

    @Transient
    public boolean isGreece() {
        return name.equalsIgnoreCase("greece");
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoneInternationalPrefix() {
        return phoneInternationalPrefix;
    }

    public void setPhoneInternationalPrefix(String phoneInternationalPrefix) {
        this.phoneInternationalPrefix = phoneInternationalPrefix;
    }
}