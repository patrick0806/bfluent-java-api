package com.bfluent.management_api.Bfluent.adapter.rest.openapi;

import com.bfluent.management_api.Bfluent.adapter.rest.dto.auth.LoginRequest;
import com.bfluent.management_api.Bfluent.adapter.rest.dto.auth.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth")
public interface AuthOpenApi {
    @Operation(summary = "Authenticate a user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User authenticated",
                    content = { @Content(schema = @Schema(implementation = LoginResponse.class), mediaType = "application/json") }
            ),
    })
    public ResponseEntity authenticate(LoginRequest loginRequest);
}
