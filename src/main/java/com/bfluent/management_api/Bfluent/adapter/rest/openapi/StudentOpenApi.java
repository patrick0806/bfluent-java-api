package com.bfluent.management_api.Bfluent.adapter.rest.openapi;

import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.CreateStudentRequest;
import com.bfluent.management_api.Bfluent.adapter.rest.dto.student.StudentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Students")
public interface StudentOpenApi {
    @Operation(summary = "Create Student")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Student created with success",
                    content = { @Content(schema = @Schema(implementation = StudentDTO.class), mediaType = "application/json") }
            ),
    })
    public ResponseEntity create(@RequestBody CreateStudentRequest studentData);
}
