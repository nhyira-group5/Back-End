--INSERT INTO meta (meta, qtd_fase)
--VALUES ('Ganho de massa muscular', 4),
--       ('Ganho de massa', 3),
--       ('Evitar sedentarismo', 2),
--       ('Perda de peso', 3);
--
--INSERT INTO usuario (username, cpf, nome, dt_nasc, genero, email, email2, senha, peso, altura, fk_meta, fk_imagem_usuario)
--VALUES ('yLu!gi22', '960.183.501-53', 'Luigi Vicchietti', '2005-01-17', 'M', 'fulano@linguica.com', 'fulano@example.com', '$2a$12$fulIzX5piuJvmwGOAkibp.WRId6EujSUnnlY.4LrdEOoz5413R0qq', 56.5, 1.85, 1, null);

INSERT INTO usuario (username, cpf, nome, dtnasc, genero, email, email2, senha, peso, altura, tipo)
VALUES ('yLu!gi22', '960.183.501', 'Luigi Vicchietti', '2005-01-17', 'M', 'fulano@linguica.com', 'fulano@example.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fP0AMMNqEnZg0695jnSkHSfkkIrC', 56.5, 1.85,'PERSONAL');
--INSERT INTO personal (username, cpf, nome, dt_nasc, genero, email, email2, senha, fk_imagem_personal)
--VALUES ('dantasWill@24', '960.183.341-12', 'Will Salada', '2004-03-31', 'M', 'will@gmail.com', 'fulano@exemple.com', '$2a$12$xj4ESCqzJuNPYFY79CYR/.HRkEweNwptVTHkmUC9IwCWlhJhkA8/G', null);