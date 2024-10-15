package com.bfluent.management_api.Bfluent.adapter.rest;

import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.AddressDTO;
import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.CreateStudentRequest;
import com.bfluent.management_api.Bfluent.domain.model.enums.DocumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class StudentControllerTest {

    @Test
    void shouldCreateStudent(){
        AddressDTO addressDTO = AddressDTO.builder()
                .neighborhood("Some Neighborhood")
                .number("68")
                .street("Some street")
                .zipCode("1387286")
                .build();

        CreateStudentRequest newStudentData = CreateStudentRequest.builder()
                .name("Test Name")
                .email("validEmail@email.com")
                .isForeigner(false)
                .address(addressDTO)
                .phoneNumber("19993912304")
                .documentType(DocumentType.CPF)
                .documentNumber("00000000000")
                .build();

        //TODO made the test
        assertThat(true).isNotNull();
    }
}