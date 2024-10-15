package com.bfluent.management_api.Bfluent.domain.model;

import lombok.Data;

@Data
public class Address {
    private String zipCode;
    private String street;
    private String number;
    private String neighborhood;
}
