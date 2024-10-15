package com.bfluent.management_api.Bfluent.adapter.rest.mapper;

import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.AddressDTO;
import com.bfluent.management_api.Bfluent.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toEntity(AddressDTO createStudentRequest);
    AddressDTO toDTO(Address student);

}