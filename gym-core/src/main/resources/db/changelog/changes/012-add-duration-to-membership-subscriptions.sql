--liquibase formatted sql

--changeset saqib:012-add-duration-to-membership-subscriptions
ALTER TABLE membership_subscriptions
    ADD COLUMN duration_in_months INT NOT NULL DEFAULT 0;
