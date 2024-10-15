package com.bfluent.management_api.Bfluent.adapter.rest.dto.student;

import com.bfluent.management_api.Bfluent.domain.model.enums.DocumentType;

import java.time.LocalDateTime;

public record StudentDTO(
        String id,
        String code,
        String name,
        String email,
        String password,
        String phoneNumber,
        AddressDTO address,
        DocumentType documentType,
        String documentNumber,
        Boolean isForeigner,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
