package com.bfluent.management_api.Bfluent.adapter.datasource.mapper;

import com.bfluent.management_api.Bfluent.adapter.datasource.service.model.AddressModel;
import com.bfluent.management_api.Bfluent.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressModelMapper {
    AddressModelMapper INSTANCE = Mappers.getMapper(AddressModelMapper.class);

    AddressModel toModel(Address address);
    Address toEntity(AddressModel addressModel);
}
