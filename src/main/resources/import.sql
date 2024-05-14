
INSERT INTO Role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO Role (authority) VALUES ('ROLE_USER');

INSERT INTO Usuario (estado_da_conta, password, foto, primeiro_nome, segundo_nome,email, telefone, role_id) VALUES (true, 'senhaSegura123', 'caminho/para/foto1.jpg', 'João', 'Silva','joa23@gmail.com', '+1234567890', 1);
INSERT INTO Usuario (estado_da_conta, password, foto, primeiro_nome, segundo_nome,email, telefone, role_id) VALUES (true, 'outraSenha456', 'caminho/para/foto2.jpg', 'Maria',  'Santos','Luis@gmail.com', '+9876543210', 1);
INSERT INTO Usuario (estado_da_conta, password, foto, primeiro_nome, segundo_nome,email, telefone, role_id) VALUES (true, 'senhaSegura789', 'caminho/para/foto3.jpg', 'Pedro',  'Oliveira','pedro23@gmail.com', '+0123456789',2);

INSERT INTO localizacao (nome) VALUES ('Localizacao 1');
INSERT INTO localizacao (nome) VALUES ('Localizacaoo 2');
INSERT INTO localizacao (nome) VALUES ('Localizacaoo 3');
INSERT INTO localizacao (nome) VALUES ('Localizacao 4');

INSERT INTO Categoria (nome) VALUES  ('Eletronicos');
INSERT INTO Categoria (nome) VALUES  ('Livros');
INSERT INTO Categoria (nome) VALUES   ('Roupas');
INSERT INTO Categoria (nome) VALUES  ('Bijuteria');
INSERT INTO Categoria (nome) VALUES  ('Sapato');
INSERT INTO Categoria (nome) VALUES   ('Outro');


INSERT INTO Estado (nome) VALUES ('Encontrado');
INSERT INTO Estado (nome) VALUES ('Perdido');

INSERT INTO item (DATA_EHORA_ENCONTRADO_OU_PERDIDO, ESTADO_DE_DEVOLUCAO, EXPRIRACAO_NO_FEED, CATEGORIA_ID, ESTADO_ID, LOCALIZACAO_ID, USUARIO_ID, DESCRICAO, FOTO, NOME,COR,MARCA,MODELO) VALUES ('2024-04-27', 1, '2024-05-27', 1, 1, 1, 1, 'Item 1: Descrição', '/caminho/para/foto1.jpg', 'Item 1',"VErmelho","Iphone","15 pro max");
INSERT INTO item (DATA_EHORA_ENCONTRADO_OU_PERDIDO, ESTADO_DE_DEVOLUCAO, EXPRIRACAO_NO_FEED, CATEGORIA_ID, ESTADO_ID, LOCALIZACAO_ID, USUARIO_ID, DESCRICAO, FOTO, NOME,COR,MARCA,MODELO) VALUES ('2024-04-28', 0, '2024-05-28', 2, 2, 2, 2, 'Item 2: Descrição', '/caminho/para/foto2.jpg', 'Item 2',"VErmelho","Iphone","15 pro max");
INSERT INTO item (DATA_EHORA_ENCONTRADO_OU_PERDIDO, ESTADO_DE_DEVOLUCAO, EXPRIRACAO_NO_FEED, CATEGORIA_ID, ESTADO_ID, LOCALIZACAO_ID, USUARIO_ID, DESCRICAO, FOTO, NOME,COR,MARCA,MODELO) VALUES ('2024-05-01', 0, '2024-06-01', 2, 2, 1, 3, 'Item 30: Descrição', '/caminho/para/foto30.jpg', 'Item 30',"VErmelho","Iphone","15 pro max");
INSERT INTO item (DATA_EHORA_ENCONTRADO_OU_PERDIDO, ESTADO_DE_DEVOLUCAO, EXPRIRACAO_NO_FEED, CATEGORIA_ID, ESTADO_ID, LOCALIZACAO_ID, USUARIO_ID, DESCRICAO, FOTO, NOME,COR,MARCA,MODELO) VALUES ('2024-05-02', 0, '2024-05-30', 1, 1, 2, 1, 'Item 4: Descrição', '/caminho/para/foto4.jpg', 'Item 4',"VErmelho","Iphone","15 pro max");
