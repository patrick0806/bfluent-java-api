package com.bfluent.management_api.Bfluent.adapter.datasource;

import com.bfluent.management_api.Bfluent.adapter.datasource.mapper.StudentModelMapper;
import com.bfluent.management_api.Bfluent.adapter.datasource.service.StudentPostgres;
import com.bfluent.management_api.Bfluent.domain.model.Page;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class StudentDatasource implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

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
    public Optional<Student> findByEmail(String email) {
        return studentPostgres.findByEmail(email).map(StudentModelMapper.INSTANCE::toEntity);
    }

    @Override
    public Page<Student> findAll(int page, int pageSize) {
        final var pageStudents = studentPostgres.findAll(PageRequest.of(page, pageSize));
        return StudentModelMapper.INSTANCE.toEntityPage(pageStudents);
    }

    @Override
    public Optional<Student> findById(UUID id) {
        final var student = studentPostgres.findById(id);
        return student.map(StudentModelMapper.INSTANCE::toEntity);
    }

    @Override
    public Long countStudents() {
        return studentPostgres.count();
    }
}
