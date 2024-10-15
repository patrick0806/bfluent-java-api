package com.bfluent.management_api.Bfluent.adapter.datasource.mapper;

import com.bfluent.management_api.Bfluent.adapter.datasource.service.model.StudentModel;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T20:11:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
public class StudentModelMapperImpl implements StudentModelMapper {

    @Override
    public StudentModel toModel(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentModel.StudentModelBuilder studentModel = StudentModel.builder();

        if ( student.getId() != null ) {
            studentModel.id( UUID.fromString( student.getId() ) );
        }
        studentModel.name( student.getName() );
        studentModel.email( student.getEmail() );
        studentModel.password( student.getPassword() );
        studentModel.isActive( student.getIsActive() );
        studentModel.createdAt( student.getCreatedAt() );
        studentModel.updatedAt( student.getUpdatedAt() );

        return studentModel.build();
    }

    @Override
    public Student toEntity(StudentModel studentModel) {
        if ( studentModel == null ) {
            return null;
        }

        Student.StudentBuilder student = Student.builder();

        if ( studentModel.getId() != null ) {
            student.id( studentModel.getId().toString() );
        }
        student.name( studentModel.getName() );
        student.email( studentModel.getEmail() );
        student.password( studentModel.getPassword() );
        student.isActive( studentModel.getIsActive() );
        student.createdAt( studentModel.getCreatedAt() );
        student.updatedAt( studentModel.getUpdatedAt() );

        return student.build();
    }
}
