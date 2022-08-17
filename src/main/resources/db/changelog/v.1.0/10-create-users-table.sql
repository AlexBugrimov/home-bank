CREATE TABLE IF NOT EXISTS users (
    id bigserial PRIMARY KEY,
    email varchar(255),
    first_name varchar(128),
    last_name  varchar(128),
    login      varchar(128) CONSTRAINT login_unique UNIQUE,
    password   varchar(255),
    role_id    bigint CONSTRAINT fk_users_roles references roles
);

GO
