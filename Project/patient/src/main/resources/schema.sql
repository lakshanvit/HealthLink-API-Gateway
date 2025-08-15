CREATE TABLE IF NOT EXISTS patient (
    patient_id SERIAL PRIMARY KEY,
    public_id VARCHAR(100) NOT NULL,
    patient_no VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(100) NOT NULL,
    CONSTRAINT uk_public_id UNIQUE (public_id),
    CONSTRAINT uk_patient_no UNIQUE (patient_no)
);