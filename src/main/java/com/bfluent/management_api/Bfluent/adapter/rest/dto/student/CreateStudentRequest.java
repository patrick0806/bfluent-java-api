package com.bfluent.management_api.Bfluent.adapter.rest.dto.student;

import com.bfluent.management_api.Bfluent.domain.model.enums.DocumentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateStudentRequest {
    @NotBlank String name;
    @NotBlank @Email String email;
    @NotBlank String phoneNumber;
    @Valid AddressDTO address;
    @NotNull DocumentType documentType;
    @NotBlank String documentNumber;
    @NotNull Boolean isForeigner;

}
