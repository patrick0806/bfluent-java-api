package com.bfluent.management_api.Bfluent.adapter.datasource;

import com.bfluent.management_api.Bfluent.adapter.datasource.mapper.StudentModelMapper;
import com.bfluent.management_api.Bfluent.adapter.datasource.service.StudentPostgres;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentDatasource implements StudentRepository {

    private final StudentPostgres studentPostgres;

    public StudentDatasource(StudentPostgres studentPostgres){
        this.studentPostgres = studentPostgres;
    }

    @Override
    public Optional<UserDetails> findByEmailForAuth(String email) {
        return studentPostgres.findByEmail(email).map(StudentModelMapper.INSTANCE::toEntity);
    }

    @Override
    public Student save(Student student) {
        final var studentModel = StudentModelMapper.INSTANCE.toModel(student);
        final var savedStudent = studentPostgres.save(studentModel);
        return StudentModelMapper.INSTANCE.toEntity(savedStudent);
    }

    @Override
    public Optional<Student> findByEmaiL(String email) {
        return studentPostgres.findByEmail(email).map(StudentModelMapper.INSTANCE::toEntity);
    }

    @Override
    public Long countStudents() {
        return studentPostgres.count();
    }
}
