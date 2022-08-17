CREATE TABLE IF NOT EXISTS roles (
    id bigserial PRIMARY KEY,
    label VARCHAR(128),
    code VARCHAR(32) CONSTRAINT code_unique UNIQUE
);

GO
