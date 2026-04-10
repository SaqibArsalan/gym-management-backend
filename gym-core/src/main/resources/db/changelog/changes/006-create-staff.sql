--liquibase formatted sql
--changeset admin:006-create-staff
CREATE TABLE IF NOT EXISTS staff (
    id          VARCHAR(255) PRIMARY KEY,
    user_id     VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    salary      DECIMAL(10, 2) NOT NULL,
    hire_date   DATE          NOT NULL,
    created_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);
