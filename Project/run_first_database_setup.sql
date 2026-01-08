-- HealthLink Microservices Database Setup Script
-- Run this BEFORE starting any microservices
-- Usage: psql -U postgres -f run_first_database_setup.sql

-- Create databases for each microservice
CREATE DATABASE patientdb;
CREATE DATABASE encounterdb;
CREATE DATABASE observationdb;

-- Create user and grant permissions
CREATE USER admin WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE patientdb TO admin;
GRANT ALL PRIVILEGES ON DATABASE encounterdb TO admin;
GRANT ALL PRIVILEGES ON DATABASE observationdb TO admin;

-- ========================================
-- PATIENT DATABASE SETUP
-- ========================================
\c patientdb;

-- Grant schema permissions to admin user
GRANT ALL ON SCHEMA public TO admin;

-- Create patient table
CREATE TABLE IF NOT EXISTS patient (
    patient_id SERIAL PRIMARY KEY,
    public_id VARCHAR(100) NOT NULL,
    patient_no VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_at TIMESTAMP,
    updated_by VARCHAR(100),
    CONSTRAINT uk_public_id UNIQUE (public_id),
    CONSTRAINT uk_patient_no UNIQUE (patient_no)
);

-- Grant table permissions to admin user
GRANT ALL PRIVILEGES ON patient TO admin;
GRANT ALL PRIVILEGES ON SEQUENCE patient_patient_id_seq TO admin;

-- Insert sample patient data
INSERT INTO patient (public_id, patient_no, first_name, last_name, birth_date, gender, created_by)
VALUES
('550e8400-e29b-41d4-a716-446655440001', 'MRN-2025-00001', 'John', 'Doe', '1990-05-15', 'male', 'system'),
('550e8400-e29b-41d4-a716-446655440002', 'MRN-2025-00002', 'Jane', 'Smith', '1985-08-22', 'female', 'system'),
('550e8400-e29b-41d4-a716-446655440003', 'MRN-2025-00003', 'Michael', 'Johnson', '1978-12-10', 'male', 'system'),
('550e8400-e29b-41d4-a716-446655440004', 'MRN-2025-00004', 'Emily', 'Davis', '1992-03-18', 'female', 'system'),
('550e8400-e29b-41d4-a716-446655440005', 'MRN-2025-00005', 'Robert', 'Wilson', '1975-11-25', 'male', 'system');

-- ========================================
-- ENCOUNTER DATABASE SETUP
-- ========================================
\c encounterdb;

-- Grant schema permissions to admin user
GRANT ALL ON SCHEMA public TO admin;

-- Create encounter table
CREATE TABLE IF NOT EXISTS encounter (
    encounter_id SERIAL PRIMARY KEY,
    visit_date DATE NOT NULL,
    reason VARCHAR(500),
    patient_no VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_at TIMESTAMP,
    updated_by VARCHAR(100)
);

-- Grant table permissions to admin user
GRANT ALL PRIVILEGES ON encounter TO admin;
GRANT ALL PRIVILEGES ON SEQUENCE encounter_encounter_id_seq TO admin;

-- Insert sample encounter data
INSERT INTO encounter (visit_date, reason, patient_no, created_by)
VALUES
('2025-01-15', 'Annual checkup and blood pressure monitoring', 'MRN-2025-00001', 'system'),
('2025-01-10', 'Follow-up for diabetes management', 'MRN-2025-00002', 'system'),
('2025-01-05', 'Chest pain evaluation', 'MRN-2025-00003', 'system'),
('2025-01-20', 'Routine physical examination', 'MRN-2025-00004', 'system'),
('2025-01-12', 'Hypertension follow-up visit', 'MRN-2025-00005', 'system'),
('2025-01-08', 'Preventive care screening', 'MRN-2025-00001', 'system'),
('2025-01-18', 'Medication review and adjustment', 'MRN-2025-00002', 'system');

-- ========================================
-- OBSERVATION DATABASE SETUP
-- ========================================
\c observationdb;

-- Grant schema permissions to admin user
GRANT ALL ON SCHEMA public TO admin;

-- Create observation table
CREATE TABLE IF NOT EXISTS observation (
    observation_id SERIAL PRIMARY KEY,
    patient_no VARCHAR(100) NOT NULL,
    bpvalue VARCHAR(50),
    bpunit VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_at TIMESTAMP,
    updated_by VARCHAR(100)
);

-- Grant table permissions to admin user
GRANT ALL PRIVILEGES ON observation TO admin;
GRANT ALL PRIVILEGES ON SEQUENCE observation_observation_id_seq TO admin;

-- Insert sample observation data
INSERT INTO observation (patient_no, bpvalue, bpunit, created_by)
VALUES
('MRN-2025-00001', '120/80', 'mmHg', 'system'),
('MRN-2025-00002', '140/90', 'mmHg', 'system'),
('MRN-2025-00003', '110/70', 'mmHg', 'system'),
('MRN-2025-00004', '118/75', 'mmHg', 'system'),
('MRN-2025-00005', '135/85', 'mmHg', 'system');

-- ========================================
-- VERIFICATION QUERIES
-- ========================================

-- Check patient data
\c patientdb;
SELECT 'PATIENT DATA:' as info;
SELECT patient_no, first_name, last_name, birth_date, gender FROM patient ORDER BY patient_no;

-- Check encounter data
\c encounterdb;
SELECT 'ENCOUNTER DATA:' as info;
SELECT patient_no, visit_date, reason FROM encounter ORDER BY visit_date DESC;

-- Check observation data
\c observationdb;
SELECT 'OBSERVATION DATA:' as info;
SELECT patient_no, bpvalue, bpunit FROM observation ORDER BY patient_no;

-- Final summary
SELECT 'DATABASE SETUP COMPLETE!' as status;
SELECT 'Created databases: patientdb, encounterdb, observationdb' as databases;
SELECT 'Created user: admin (password: admin)' as user_info;
SELECT 'Sample data: 5 patients, 7 encounters, 5 observations' as sample_data;