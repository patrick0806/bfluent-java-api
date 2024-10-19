package com.bfluent.management_api.Bfluent.domain.interector.student;

import com.bfluent.management_api.Bfluent.domain.exceptions.EntityNotFound;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStudentByIdUseCaseTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private GetStudentByIdUseCase getStudentByIdUseCase;

    private Student student = Student.builder()
            .id(UUID.randomUUID().toString())
            .name("some name")
            .build();

    @Test
    void shouldGetStudentById() {
        when(repository.findById(UUID.fromString(student.getId()))).thenReturn(Optional.of(student));
        final var findedStudent = getStudentByIdUseCase.execute(student.getId());

        assertEquals(student.getId(), findedStudent.getId());
        assertEquals(student.getName(), findedStudent.getName());
    }

    @Test
    void shouldThrowExceptionIfStudentNotFound() {
        when(repository.findById(UUID.fromString(student.getId()))).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> getStudentByIdUseCase.execute(student.getId()));
    }
}