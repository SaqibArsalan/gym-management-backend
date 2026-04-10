--liquibase formatted sql
--changeset admin:008-create-sessions
CREATE TABLE IF NOT EXISTS sessions (
    id           VARCHAR(255) PRIMARY KEY,
    class_id     VARCHAR(255) NOT NULL,
    session_date TIMESTAMP    NOT NULL,
    trainer_id   VARCHAR(255) NOT NULL,
    capacity     INT          NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_session_class FOREIGN KEY (class_id) REFERENCES classes(id)
);
