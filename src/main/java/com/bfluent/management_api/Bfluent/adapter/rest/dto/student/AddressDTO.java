package com.bfluent.management_api.Bfluent.adapter.rest.dto.student;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressDTO{
      @NotBlank String zipCode;
      @NotBlank  String street;
      @NotBlank  String number;
      @NotBlank  String neighborhood;
}
