--liquibase formatted sql

--changeset saqib:011-create-gym-attendance
CREATE TABLE IF NOT EXISTS gym_attendance (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    check_in_time TIMESTAMP NOT NULL,
    check_out_time TIMESTAMP,
    marked_by VARCHAR(255),
    attendee_type VARCHAR(50) NOT NULL,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_gym_attendance_user FOREIGN KEY (user_id) REFERENCES users(id)
    );
