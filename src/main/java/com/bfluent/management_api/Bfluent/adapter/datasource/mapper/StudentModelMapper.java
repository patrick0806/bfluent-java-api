package com.bfluent.management_api.Bfluent.adapter.datasource.mapper;

import com.bfluent.management_api.Bfluent.adapter.datasource.service.model.StudentModel;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentModelMapper {
    StudentModelMapper INSTANCE = Mappers.getMapper(StudentModelMapper.class);

    StudentModel toModel(Student student);
    Student toEntity(StudentModel studentModel);
}
