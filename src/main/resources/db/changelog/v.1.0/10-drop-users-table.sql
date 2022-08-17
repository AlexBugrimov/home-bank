ALTER TABLE users
    DROP CONSTRAINT login_unique;

GO

ALTER TABLE users
    DROP CONSTRAINT fk_users_roles;

GO

DROP TABLE users;

GO