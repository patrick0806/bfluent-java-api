package com.bfluent.management_api.Bfluent.adapter.datasource.service.model;

import com.bfluent.management_api.Bfluent.domain.model.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "students")
@Entity
@Data
@Builder
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String code;
    private String name;
    private String email;
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Embedded
    private AddressModel address;

    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "is_foreign")
    private Boolean isForeigner = false;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
