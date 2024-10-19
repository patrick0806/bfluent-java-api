package com.bfluent.management_api.Bfluent.adapter.rest;

import com.bfluent.management_api.Bfluent.adapter.rest.dto.PageDTO;
import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.CreateStudentRequest;
import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.StudentDTO;
import com.bfluent.management_api.Bfluent.adapter.rest.mapper.StudentMapper;
import com.bfluent.management_api.Bfluent.adapter.rest.openapi.StudentOpenApi;
import com.bfluent.management_api.Bfluent.domain.interector.student.CreateStudentUseCase;
import com.bfluent.management_api.Bfluent.domain.interector.student.GetStudentByIdUseCase;
import com.bfluent.management_api.Bfluent.domain.interector.student.ListStudentsUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController implements StudentOpenApi {

    private final CreateStudentUseCase createStudentUseCase;
    private final ListStudentsUseCase listStudentsUseCase;
    private final GetStudentByIdUseCase getStudentByIdUseCase;

    public StudentController(CreateStudentUseCase createStudentUseCase, ListStudentsUseCase listStudentsUseCase, GetStudentByIdUseCase getStudentByIdUseCase){
        this.createStudentUseCase = createStudentUseCase;
        this.listStudentsUseCase = listStudentsUseCase;
        this.getStudentByIdUseCase = getStudentByIdUseCase;

    }

    @Override
    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody CreateStudentRequest studentData) {
        final var createdStudent = this.createStudentUseCase.execute(StudentMapper.INSTANCE.toEntity(studentData));
        return ResponseEntity.status(HttpStatus.CREATED).body(StudentMapper.INSTANCE.toDTO(createdStudent));
    }

    @GetMapping
    public ResponseEntity<PageDTO<StudentDTO>> list(@RequestParam int page, @RequestParam int pageSize) {
        final var searchResult = this.listStudentsUseCase.execute(page, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(StudentMapper.INSTANCE.toEntityPage(searchResult));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id) {
        final var student = this.getStudentByIdUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(StudentMapper.INSTANCE.toDTO(student));
    }
}
