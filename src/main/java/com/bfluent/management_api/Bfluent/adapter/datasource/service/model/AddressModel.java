package com.bfluent.management_api.Bfluent.adapter.datasource.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class AddressModel {

    @Column(name = "zip_code")
    private String zipCode;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String neighborhood;
}