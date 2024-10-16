INSERT INTO Usuario (nome, cpf, telefone, data_nascimento, email, senha, tipo)
    VALUES ('Matheus Rabello', '949.606.250-40', '11992247954',
            '2003-11-08', 'matheus.csantos@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 3);

INSERT INTO Usuario (nome, cpf, telefone, data_nascimento, email, senha, tipo)
    VALUES ('Pedro Saraujo', '46553098069', '13981309594',
            '2005-05-13', 'pedro.saraujo@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 3);

INSERT INTO Produto (nome, tamanho, cor, tipo, categoria, sub_categoria, preco, descricao, url_imagem)
VALUES
('Produto 1', 1, 'Azul', 2, 'Acessorio', 'Tudo', 100.0, 'Descrição do produto 1', 'http://exemplo.com/imagem1.jpg'),
('Produto 2', 1, 'Vermelho', 2, 'Acessorio', 'Tudo', 110.0, 'Descrição do produto 2', 'http://exemplo.com/imagem2.jpg'),
('Produto 3', 1, 'Verde', 2, 'Acessorio', 'Tudo', 120.0, 'Descrição do produto 3', 'http://exemplo.com/imagem3.jpg'),
('Produto 4', 1, 'Amarelo', 2, 'Acessorio', 'Tudo', 130.0, 'Descrição do produto 4', 'http://exemplo.com/imagem4.jpg'),
('Produto 5', 1, 'Preto', 2, 'Acessorio', 'Tudo', 140.0, 'Descrição do produto 5', 'http://exemplo.com/imagem5.jpg'),
('Produto 6', 1, 'Branco', 2, 'Acessorio', 'Tudo', 150.0, 'Descrição do produto 6', 'http://exemplo.com/imagem6.jpg'),
('Produto 7', 1, 'Cinza', 2, 'Acessorio', 'Tudo', 160.0, 'Descrição do produto 7', 'http://exemplo.com/imagem7.jpg'),
('Produto 8', 1, 'Roxo', 2, 'Acessorio', 'Tudo', 170.0, 'Descrição do produto 8', 'http://exemplo.com/imagem8.jpg'),
('Produto 9', 1, 'Laranja', 2, 'Acessorio', 'Tudo', 180.0, 'Descrição do produto 9', 'http://exemplo.com/imagem9.jpg'),
('Produto 10', 1, 'Marrom', 2, 'Acessorio', 'Tudo', 190.0, 'Descrição do produto 10', 'http://exemplo.com/imagem10.jpg'),
('Produto 11', 2, 'Azul', 2, 'Acessorio', 'Tudo', 200.0, 'Descrição do produto 11', 'http://exemplo.com/imagem11.jpg'),
('Produto 12', 2, 'Vermelho', 2, 'Acessorio', 'Tudo', 210.0, 'Descrição do produto 12', 'http://exemplo.com/imagem12.jpg'),
('Produto 13', 2, 'Verde', 2, 'Acessorio', 'Tudo', 220.0, 'Descrição do produto 13', 'http://exemplo.com/imagem13.jpg'),
('Produto 14', 2, 'Amarelo', 2, 'Acessorio', 'Tudo', 230.0, 'Descrição do produto 14', 'http://exemplo.com/imagem14.jpg'),
('Produto 15', 2, 'Preto', 2, 'Acessorio', 'Tudo', 240.0, 'Descrição do produto 15', 'http://exemplo.com/imagem15.jpg'),
('Produto 16', 3, 'Branco', 2, 'Acessorio', 'Tudo', 250.0, 'Descrição do produto 16', 'http://exemplo.com/imagem16.jpg'),
('Produto 17', 3, 'Cinza', 2, 'Acessorio', 'Tudo', 260.0, 'Descrição do produto 17', 'http://exemplo.com/imagem17.jpg'),
('Produto 18', 3, 'Roxo', 2, 'Acessorio', 'Tudo', 270.0, 'Descrição do produto 18', 'http://exemplo.com/imagem18.jpg'),
('Produto 19', 3, 'Laranja', 2, 'Acessorio', 'Tudo', 280.0, 'Descrição do produto 19', 'http://exemplo.com/imagem19.jpg'),
('Produto 20', 3, 'Marrom', 2, 'Acessorio', 'Tudo', 290.0, 'Descrição do produto 20', 'http://exemplo.com/imagem20.jpg'),
('Produto 21', 3, 'Azul', 2, 'Acessorio', 'Tudo', 300.0, 'Descrição do produto 21', 'http://exemplo.com/imagem21.jpg'),
('Produto 22', 3, 'Vermelho', 2, 'Acessorio', 'Tudo', 310.0, 'Descrição do produto 22', 'http://exemplo.com/imagem22.jpg'),
('Produto 23', 3, 'Verde', 2, 'Acessorio', 'Tudo', 320.0, 'Descrição do produto 23', 'http://exemplo.com/imagem23.jpg'),
('Produto 24', 3, 'Amarelo', 2, 'Acessorio', 'Tudo', 330.0, 'Descrição do produto 24', 'http://exemplo.com/imagem24.jpg'),
('Produto 25', 3, 'Preto', 2, 'Acessorio', 'Tudo', 340.0, 'Descrição do produto 25', 'http://exemplo.com/imagem25.jpg');

INSERT INTO Pedido (data, valor_total, status, usuario_id)
VALUES
(NOW(), 0.0, 1, 1),
(NOW(), 0.0, 1, 1),
(NOW(), 0.0, 1, 1),
(NOW(), 0.0, 1, 1),
(NOW(), 0.0, 1, 1);

-- Pedido 1 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade, sub_total)
VALUES
(1, 1, 1, 100.0),
(2, 2, 1, 110.0),
(3, 3, 1, 120.0),
(4, 4, 1, 130.0),
(5, 5, 1, 140.0);

-- Pedido 2 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade, sub_total)
VALUES
(1, 6, 1, 100.0),
(2, 7, 1, 110.0),
(3, 8, 1, 120.0),
(4, 9, 1, 130.0),
(5, 10, 1, 140.0);

-- Pedido 3 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade, sub_total)
VALUES
(1, 11, 1, 100.0),
(2, 12, 1, 110.0),
(3, 13, 1, 120.0),
(4, 14, 1, 130.0),
(5, 15, 1, 140.0);

-- Pedido 4 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade, sub_total)
VALUES
(1, 16, 1, 100.0),
(2, 17, 1, 110.0),
(3, 18, 1, 120.0),
(4, 19, 1, 130.0),
(5, 20, 1, 140.0);

-- Pedido 5 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade, sub_total)
VALUES
(1, 21, 1, 100.0),
(2, 22, 1, 110.0),
(3, 23, 1, 120.0),
(4, 24, 1, 130.0),
(5, 25, 1, 140.0);

-- Atualizar valor total para Pedido 1
UPDATE Pedido
SET valor_total = (SELECT SUM(sub_total) FROM Item_Pedido WHERE pedido_id = 1)
WHERE id = 1;

-- Repetir para os demais pedidos
UPDATE Pedido
SET valor_total = (SELECT SUM(sub_total) FROM Item_Pedido WHERE pedido_id = 2)
WHERE id = 2;

UPDATE Pedido
SET valor_total = (SELECT SUM(sub_total) FROM Item_Pedido WHERE pedido_id = 3)
WHERE id = 3;

UPDATE Pedido
SET valor_total = (SELECT SUM(sub_total) FROM Item_Pedido WHERE pedido_id = 4)
WHERE id = 4;

UPDATE Pedido
SET valor_total = (SELECT SUM(sub_total) FROM Item_Pedido WHERE pedido_id = 5)
WHERE id = 5;
