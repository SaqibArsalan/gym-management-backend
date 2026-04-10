--liquibase formatted sql
--changeset admin:005-create-membership-subscriptions
CREATE TABLE IF NOT EXISTS membership_subscriptions (
    id                  VARCHAR(255) PRIMARY KEY,
    user_id             VARCHAR(255) NOT NULL,
    name                VARCHAR(255) NOT NULL,
    join_date           DATE         NOT NULL,
    expiry_date         DATE         NOT NULL,
    status              VARCHAR(50)  NOT NULL DEFAULT 'ACTIVE',
    membership_plan_id  VARCHAR(255) NOT NULL,
    created_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_membership_plan FOREIGN KEY (membership_plan_id) REFERENCES membership_plans(id)
);
