package com.bfluent.management_api.Bfluent.adapter.datasource.service;

import com.bfluent.management_api.Bfluent.adapter.datasource.service.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentPostgres extends JpaRepository<StudentModel, UUID> {
    Optional<StudentModel> findByEmail(String email);
}
