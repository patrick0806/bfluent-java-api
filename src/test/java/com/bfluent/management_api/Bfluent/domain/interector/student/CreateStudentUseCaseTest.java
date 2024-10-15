package com.bfluent.management_api.Bfluent.domain.interector.student;

import com.bfluent.management_api.Bfluent.domain.exceptions.AlreadyExistsException;
import com.bfluent.management_api.Bfluent.domain.model.Address;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateStudentUseCaseTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private CreateStudentUseCase createStudentUseCase;

    Student student;

    @BeforeEach
    void setUp(){
        final var address = Address.builder()
                .neighborhood("some neighborhood")
                .number("some number")
                .street("some street")
                .zipCode("some zip code")
                .build();
        student = Student.builder()
                .name("some name")
                .email("some email")
                .password("some password")
                .build();
    }

    @Test
    void shouldCreateStudent() {
        when(passwordEncoder.encode(student.getPassword())).thenReturn("encripted");
        when(repository.findByEmail(student.getEmail())).thenReturn(Optional.empty());
        when(repository.countStudents()).thenReturn(0L);
        when(repository.save(student)).thenReturn(student);

        final var createdStudent = createStudentUseCase.execute(student);

        assertNotNull(createdStudent);
        assertNotNull(createdStudent.getCode());
        assertEquals(student.getCode(), "SN-0001-S");
        assertEquals(student.getName(), createdStudent.getName());
        assertEquals(createdStudent.getPassword(), "encripted");
    }

    @Test
    void shouldFailIfStudentAlreadyExist() {
        when(repository.findByEmail(student.getEmail())).thenReturn(Optional.of(student));
        assertThrows(AlreadyExistsException.class, () -> createStudentUseCase.execute(student));
    }
}