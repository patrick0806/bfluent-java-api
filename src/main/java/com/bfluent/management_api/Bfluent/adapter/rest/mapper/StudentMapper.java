package com.bfluent.management_api.Bfluent.adapter.rest.mapper;

import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.CreateStudentRequest;
import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.StudentDTO;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(CreateStudentRequest createStudentRequest);
    StudentDTO toDTO(Student student);

}
