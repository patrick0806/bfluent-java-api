package com.bfluent.management_api.Bfluent.domain.interector.student;

import com.bfluent.management_api.Bfluent.domain.exceptions.EntityNotFound;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetStudentByIdUseCase {
    private final StudentRepository repository;

    public GetStudentByIdUseCase(StudentRepository repository) {
        this.repository = repository;
    }

    public Student execute(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFound(String.format("Student with id: %s not found.", id)));
    }
}
