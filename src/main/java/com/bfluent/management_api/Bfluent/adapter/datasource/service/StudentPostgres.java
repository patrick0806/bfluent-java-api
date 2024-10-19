package com.bfluent.management_api.Bfluent.adapter.datasource.service;

import com.bfluent.management_api.Bfluent.adapter.datasource.service.model.StudentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentPostgres extends JpaRepository<StudentModel, UUID> {
    Optional<StudentModel> findByEmail(String email);
    Page<StudentModel> findAll(Pageable pageable);
    Optional<StudentModel> findById(UUID id);
}
