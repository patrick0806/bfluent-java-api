package com.bfluent.management_api.Bfluent.adapter.rest;

import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.CreateStudentRequest;
import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.StudentDTO;
import com.bfluent.management_api.Bfluent.adapter.rest.mapper.StudentMapper;
import com.bfluent.management_api.Bfluent.adapter.rest.openapi.StudentOpenApi;
import com.bfluent.management_api.Bfluent.domain.interector.student.CreateStudentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController implements StudentOpenApi {

    private final CreateStudentUseCase createStudentUseCase;

    public StudentController(CreateStudentUseCase createStudentUseCase){
        this.createStudentUseCase = createStudentUseCase;
    }

    @Override
    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody CreateStudentRequest studentData) {
        final var createdStudent = this.createStudentUseCase.execute(StudentMapper.INSTANCE.toEntity(studentData));
        return ResponseEntity.status(HttpStatus.CREATED).body(StudentMapper.INSTANCE.toDTO(createdStudent));
    }
}
