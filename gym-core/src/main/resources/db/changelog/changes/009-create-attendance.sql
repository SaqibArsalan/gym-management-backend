--liquibase formatted sql
--changeset admin:009-create-attendance
CREATE TABLE IF NOT EXISTS attendance (
    id              VARCHAR(255) PRIMARY KEY,
    member_id       VARCHAR(255) NOT NULL,
    session_id      VARCHAR(255) NOT NULL,
    status          VARCHAR(50)  NOT NULL,
    check_in_time   TIMESTAMP    NOT NULL,
    check_out_time  TIMESTAMP,
    created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_attendance_session FOREIGN KEY (session_id) REFERENCES sessions(id)
);
