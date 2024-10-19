package com.bfluent.management_api.Bfluent.domain.interector.student;

import com.bfluent.management_api.Bfluent.domain.model.Page;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class ListStudentsUseCase {
    private final StudentRepository repository;

    public ListStudentsUseCase(StudentRepository repository) {
        this.repository = repository;
    }

    public Page<Student> execute(int page, int pageSize) {
        return repository.findAll(page, pageSize);
    }
}
