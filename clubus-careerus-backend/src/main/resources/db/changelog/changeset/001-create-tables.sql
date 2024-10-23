--liquibase formatted sql

--changeset liquibase-docs:create-table

CREATE TABLE users (
    email VARCHAR(50) NOT NULL PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    password VARCHAR NOT NULL,
    created_at timestamp
);

