CREATE TABLE IF NOT EXISTS patient (
    `patient_id` int AUTO_INCREMENT PRIMARY KEY,
    `public_id` VARCHAR(100) NOT NULL,
    `patient_no` VARCHAR(100) NOT NULL,
    `first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(100) NOT NULL,
    `birth_date` DATE NOT NULL,
    `gender` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) DEFAULT NULL,
    `mobileNo` VARCHAR(100) DEFAULT NULL,
    `communication_sw` BOOLEAN,
--    `created_at` date NOT NULL,
--    `created_by` varchar(20) NOT NULL,
--    `updated_at` date DEFAULT NULL,
--    `updated_by` varchar(20) DEFAULT NULL,
    CONSTRAINT uk_public_id UNIQUE (public_id),
    CONSTRAINT uk_patient_no UNIQUE (patient_no)
);