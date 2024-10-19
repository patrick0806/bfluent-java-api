package com.bfluent.management_api.Bfluent.adapter.datasource.mapper;

import com.bfluent.management_api.Bfluent.adapter.datasource.service.model.StudentModel;
import com.bfluent.management_api.Bfluent.domain.model.Page;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentModelMapper {
    StudentModelMapper INSTANCE = Mappers.getMapper(StudentModelMapper.class);

    StudentModel toModel(Student student);
    Student toEntity(StudentModel studentModel);
    List<Student>  toEntityList(List<StudentModel> studentModels);

    @Mapping(target = "page", source = "number")
    Page<Student> toEntityPage(org.springframework.data.domain.Page<StudentModel> studentModels);
}
