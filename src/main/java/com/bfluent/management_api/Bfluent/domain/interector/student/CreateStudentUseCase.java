package com.bfluent.management_api.Bfluent.domain.interector.student;

import com.bfluent.management_api.Bfluent.domain.exceptions.AlreadyExistsException;
import com.bfluent.management_api.Bfluent.domain.model.Student;
import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CreateStudentUseCase {
    private final StudentRepository repository;

    public CreateStudentUseCase(StudentRepository repository){
        this.repository = repository;
    }

    public Student execute(Student studentData){
        this.repository.findByEmaiL(studentData.getEmail()).ifPresent(student->{
            throw new AlreadyExistsException(String
                    .format("A student with email: %s already exist.", student.getCreatedAt()));
        });
        final var code = generateStudentCode(studentData.getName());
        studentData.setCode(code);

        return this.repository.save(studentData);
    }

    private String generateStudentCode(String name){
        var amountStudents = this.repository.countStudents();

        final var resumeName = Arrays
                .stream(name.split(" "))
                .map(n -> name.toUpperCase().charAt(0))
                .toString()
                .replace(",","");

        amountStudents += 1;
        String numericValue;
        if (amountStudents < 10) {
            numericValue = String.format("000%d",amountStudents);
        } else if (amountStudents < 100) {
            numericValue = String.format("00%d",amountStudents);
        } else if (amountStudents < 1000) {
            numericValue = String.format("0%d",amountStudents);
        } else {
            numericValue = String.format("000%d",amountStudents);
        }
        return String.format("%s-%s-%s", resumeName,numericValue,"S");
    }
}
