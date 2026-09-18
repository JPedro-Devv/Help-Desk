CREATE TYPE perfil AS ENUM('ROLE_CLIENTE', 'ROLE_TECNICO', 'ROLE_ADMIN');

CREATE TABLE tb_usuarios(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL CONSTRAINT tb_usuarios_email_unique UNIQUE,
    nome VARCHAR(255) NOT NULL,
    perfil perfil NOT NULL DEFAULT 'ROLE_CLIENTE',
    senha VARCHAR(255) NOT NULL
);
