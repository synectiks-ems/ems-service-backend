package com.synectiks.cms.graphql.types.Country;

import com.synectiks.cms.entities.Country;

public class AbstractCountryPayload {
    private final Country country;

    public AbstractCountryPayload(Country country){
        this.country = country;
    }

    public Country getCountry(){
        return country;
    }
}
