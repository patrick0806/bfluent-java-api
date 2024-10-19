package com.bfluent.management_api.Bfluent.domain.repository;

import com.bfluent.management_api.Bfluent.domain.model.Page;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {
    Student save(Student student);
    Optional<UserDetails> findByEmailForAuth(String email);
    Optional<Student> findByEmail(String email);
    Long countStudents();
    Page<Student> findAll(int page, int pageSize);
    Optional<Student> findById(UUID id);
}
