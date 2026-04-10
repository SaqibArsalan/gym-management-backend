--liquibase formatted sql
--changeset admin:004-create-membership-plans
CREATE TABLE IF NOT EXISTS membership_plans (
    id                  VARCHAR(255) PRIMARY KEY,
    name                VARCHAR(50)     NOT NULL,
    duration_in_months  INT             NOT NULL,
    price               DECIMAL(10, 2)  NOT NULL,
    description         TEXT,
    created_at          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);
