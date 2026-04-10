--liquibase formatted sql
--changeset admin:010-create-enrollment
CREATE TABLE IF NOT EXISTS enrollment (
    id                  VARCHAR(255) PRIMARY KEY,
    session_id          VARCHAR(255) NOT NULL,
    membership_id       VARCHAR(255) NOT NULL,
    user_id             VARCHAR(255) NOT NULL,
    member_name         VARCHAR(255) NOT NULL,
    session_date        DATE         NOT NULL,
    class_name          VARCHAR(255) NOT NULL,
    status              VARCHAR(50)  NOT NULL DEFAULT 'ENROLLED',
    attendance_status   VARCHAR(50)  NOT NULL DEFAULT 'PENDING',
    enrolled_at         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_enrollment_session FOREIGN KEY (session_id) REFERENCES sessions(id),
    CONSTRAINT uq_enrollment_session_membership UNIQUE (session_id, membership_id)
);
