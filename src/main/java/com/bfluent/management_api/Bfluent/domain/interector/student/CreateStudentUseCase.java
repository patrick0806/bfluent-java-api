package com.bfluent.management_api.Bfluent.domain.interector.student;

import com.bfluent.management_api.Bfluent.domain.exceptions.AlreadyExistsException;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CreateStudentUseCase {
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository repository;

    public CreateStudentUseCase(StudentRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student execute(Student studentData){
        this.repository.findByEmail(studentData.getEmail()).ifPresent(student->{
            throw new AlreadyExistsException(String
                    .format("A student with email: %s already exist.", student.getEmail()));
        });
        final var code = generateStudentCode(studentData.getName());
        studentData.setCode(code);
        studentData.setPassword(passwordEncoder.encode(studentData.getPassword()));
        return this.repository.save(studentData);
    }

    private String generateStudentCode(String name){
        long studentCount = repository.countStudents();

        List<String> initials = Arrays.stream(name.split("\\s+"))
                .map(word -> String.format("%s", Character.toUpperCase(word.charAt(0))))
                .toList();
        String formattedName = initials.toString().replaceAll("\\W", "");
        String numericValue = String.format("%04d", studentCount + 1);

        return String.join("-", formattedName, numericValue, "S");
    }
}
