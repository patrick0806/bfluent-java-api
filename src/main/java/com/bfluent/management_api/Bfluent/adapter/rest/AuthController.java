package com.bfluent.management_api.Bfluent.adapter.rest;

import com.bfluent.management_api.Bfluent.adapter.rest.dto.auth.LoginRequest;
import com.bfluent.management_api.Bfluent.adapter.rest.dto.auth.LoginResponse;
import com.bfluent.management_api.Bfluent.adapter.rest.openapi.AuthOpenApi;
import com.bfluent.management_api.Bfluent.domain.interector.auth.LoginUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthOpenApi {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) {
        var accessToken = loginUseCase.execute(loginRequest.email(), loginRequest.password());

        return ResponseEntity.ok(new LoginResponse(accessToken));
    }

}
