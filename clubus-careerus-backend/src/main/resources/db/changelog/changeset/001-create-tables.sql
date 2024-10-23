--liquibase formatted sql

--changeset liquibase-docs:create-table

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    login VARCHAR(50) NOT NULL,
    password VARCHAR NOT NULL,
    created_at timestamp
);

