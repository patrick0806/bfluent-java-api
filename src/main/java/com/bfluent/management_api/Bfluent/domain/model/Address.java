package com.bfluent.management_api.Bfluent.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String zipCode;
    private String street;
    private String number;
    private String neighborhood;
}
