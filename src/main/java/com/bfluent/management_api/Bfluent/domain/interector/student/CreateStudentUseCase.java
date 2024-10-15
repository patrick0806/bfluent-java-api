package com.bfluent.management_api.Bfluent.domain.interector.student;

import com.bfluent.management_api.Bfluent.domain.exceptions.AlreadyExistsException;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateStudentUseCase {
    private final StudentRepository repository;

    public CreateStudentUseCase(StudentRepository repository){
        this.repository = repository;
    }

    public Student execute(Student studentData){
        this.repository.findByEmaiL(studentData.getEmail()).ifPresent(student->{
            throw new AlreadyExistsException(String
                    .format("A student with email: %s already exist.", student.getEmail()));
        });
        final var code = generateStudentCode(studentData.getName());
        studentData.setCode(code);

        return this.repository.save(studentData);
    }

    private String generateStudentCode(String name){
        long studentCount = repository.countStudents();

        List<String> initials = Arrays.stream(name.split("\\s+"))
                .map(word -> String.format("%s", Character.toUpperCase(word.charAt(0))))
                .collect(Collectors.toList());
        String formattedName = initials.toString().replaceAll("\\W", "");
        String numericValue = String.format("%04d", studentCount + 1);

        return String.join("-", formattedName, numericValue, "S");
    }
}
