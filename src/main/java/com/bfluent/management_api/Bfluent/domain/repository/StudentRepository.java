package com.bfluent.management_api.Bfluent.domain.repository;

import com.bfluent.management_api.Bfluent.domain.model.Student;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<UserDetails> findByEmailForAuth(String email);
    Optional<Student> findByEmaiL(String email);
    Long countStudents();
}
