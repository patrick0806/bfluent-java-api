CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE students (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    code VARCHAR(255),
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    phone_number VARCHAR(20),
    document_type VARCHAR(50),
    document_number VARCHAR(100),
    is_foreign BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    zip_code VARCHAR(15),
    street VARCHAR(255),
    number VARCHAR(20),
    neighborhood VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);