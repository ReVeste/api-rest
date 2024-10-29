INSERT INTO Usuario (nome, cpf, telefone, data_nascimento, email, senha, tipo, ativo)
    VALUES ('Matheus Rabello', '94960625040', '11992247954',
            '2003-11-08', 'matheus.csantos@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'cliente', true);

INSERT INTO Usuario (nome, cpf, telefone, data_nascimento, email, senha, tipo, ativo)
    VALUES ('Pedro Saraujo', '46553098069', '13981309594',
            '2005-05-13', 'pedro.saraujo@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'cliente', true);

INSERT INTO Produto (nome, tamanho, qualidade, categoria, preco, descricao, qtd_estoque, status)
VALUES
('Camiseta Básica Branca', 'M', 9, 'Roupas', 49.90, 'Camiseta de algodão básica branca', 1, 'DISPONIVEL'),
('Calça Jeans Slim', 'G', 8, 'Roupas', 89.90, 'Calça jeans modelo slim fit', 2, 'DISPONIVEL'),
('Vestido Midi Estampado', 'P', 7, 'Roupas', 119.90, 'Vestido midi com estampa floral', 1, 'DISPONIVEL'),
('Jaqueta Jeans', 'GG', 9, 'Roupas', 199.90, 'Jaqueta jeans clássica', 1, 'RESERVADO'),
('Saia Lápis Preta', 'M', 8, 'Roupas', 79.90, 'Saia lápis preta ajustada', 2, 'DISPONIVEL'),
('Camiseta Estampada Vintage', 'P', 9, 'Roupas', 59.90, 'Camiseta com estampa vintage retrô', 3, 'DISPONIVEL'),
('Cardigan de Tricô', 'GG', 10, 'Roupas', 199.90, 'Cardigan de tricô confortável e quentinho', 1, 'RESERVADO'),
('Blusa Canelada', 'PP', 7, 'Roupas', 69.90, 'Blusa canelada com gola alta', 4, 'DISPONIVEL'),
('Calça Legging', 'M', 8, 'Roupas', 59.90, 'Calça legging preta esportiva', 3, 'DISPONIVEL'),
('Camisa Social Listrada', 'G', 9, 'Roupas', 99.90, 'Camisa social listrada azul', 2, 'DISPONIVEL'),
('Blusa de Frio com Capuz', 'GG', 9, 'Roupas', 149.90, 'Blusa de frio com capuz e bolso frontal', 1, 'RESERVADO'),
('Colete de Nylon', 'M', 8, 'Roupas', 89.90, 'Colete leve de nylon para meia estação', 1, 'DISPONIVEL'),
('Blusa Cropped', 'P', 7, 'Roupas', 49.90, 'Blusa cropped de algodão', 2, 'DISPONIVEL'),
('Cinto de Couro Preto', 'UNICO', 9, 'Acessórios', 69.90, 'Cinto de couro preto com fivela dourada', 2, 'RESERVADO'),
('Gorro de Lã Cinza', 'UNICO', 8, 'Acessórios', 39.90, 'Gorro de lã cinza ideal para o inverno', 5, 'DISPONIVEL'),
('Cachecol de Tricô', 'UNICO', 9, 'Acessórios', 49.90, 'Cachecol de tricô com franjas nas pontas', 3, 'DISPONIVEL'),
('Pulseira de Couro', 'UNICO', 7, 'Acessórios', 29.90, 'Pulseira de couro com detalhe em metal', 4, 'DISPONIVEL'),
('Relógio Minimalista', 'UNICO', 10, 'Acessórios', 299.90, 'Relógio minimalista com pulseira de couro', 1, 'VENDIDO'),
('Bolsa Tiracolo', 'UNICO', 8, 'Acessórios', 149.90, 'Bolsa tiracolo em couro sintético', 1, 'RESERVADO'),
('Óculos de Sol Redondo', 'UNICO', 9, 'Acessórios', 99.90, 'Óculos de sol redondo estilo retrô', 3, 'DISPONIVEL'),
('Camisa Polo Básica', 'M', 8, 'Roupas', 79.90, 'Camisa polo básica com gola tradicional', 2, 'DISPONIVEL'),
('Blazer Slim Fit', 'G', 9, 'Roupas', 199.90, 'Blazer slim fit para ocasiões formais', 1, 'RESERVADO'),
('Calça de Sarja', 'GG', 8, 'Roupas', 89.90, 'Calça de sarja caqui ajustada', 2, 'DISPONIVEL'),
('Jaqueta de Sarja', 'P', 9, 'Roupas', 189.90, 'Jaqueta de sarja com bolso frontal', 1, 'DISPONIVEL'),
('Cinto Trançado', 'UNICO', 8, 'Acessórios', 59.90, 'Cinto trançado com fivela metálica', 2, 'DISPONIVEL'),
('Brinco de Argola', 'UNICO', 7, 'Acessórios', 19.90, 'Brinco de argola dourada', 6, 'DISPONIVEL'),
('Boné Estampado', 'UNICO', 8, 'Acessórios', 49.90, 'Boné com estampa geométrica', 2, 'DISPONIVEL'),
('Mochila de Couro', 'UNICO', 9, 'Acessórios', 199.90, 'Mochila de couro sintético marrom', 1, 'RESERVADO'),
('Bermuda Cargo', 'G', 8, 'Roupas', 69.90, 'Bermuda cargo com bolsos laterais', 3, 'DISPONIVEL'),
('Blusa de Manga Longa', 'GG', 9, 'Roupas', 79.90, 'Blusa de manga longa em algodão leve', 1, 'DISPONIVEL');

-- Vamos adicionar 4 imagens para cada produto na lista
INSERT INTO Imagem (produto_id, imagem_url)
VALUES
    -- Camiseta Básica Branca
    (1, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (1, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (1, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (1, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (2, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (2, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (2, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (2, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (3, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (3, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (3, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (3, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (4, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (4, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (4, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (4, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (5, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (5, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (5, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (5, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (6, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (6, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (6, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (6, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (7, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (7, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (7, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (7, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (8, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (8, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (8, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (8, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (9, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (9, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (9, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (9, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (10, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (10, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (10, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (10, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (11, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (11, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (11, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (11, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (12, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (12, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (12, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (12, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (13, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (13, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (13, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (13, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (14, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (14, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (14, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (14, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (15, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (15, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (15, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (15, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (16, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (16, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (16, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (16, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (17, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (17, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (17, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (17, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (18, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (18, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (18, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (18, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (19, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (19, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (19, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (19, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (20, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (20, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (20, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (20, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (21, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (21, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (21, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (21, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (22, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (22, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (22, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (22, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (23, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (23, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (23, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (23, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (24, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (24, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (24, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (24, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (25, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (25, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (25, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (25, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (26, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (26, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (26, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (26, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (27, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (27, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (27, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (27, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (28, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (28, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (28, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (28, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (29, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (29, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (29, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (29, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (30, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (30, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (30, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg'),
    (30, 'https://blog.casadoprodutor.com.br/wp-content/uploads/2018/04/gatinho.jpg');




INSERT INTO Pedido (data_hora, tipo_frete, valor_frete, valor_total, status, usuario_id)
VALUES
(NOW(), 'Comum', 0.0, 0.0, 'CONCLUIDO', 1),
(NOW(), 'Comum', 0.0, 0.0, 'CONCLUIDO', 2),
(NOW(), 'Comum', 0.0, 0.0, 'CONCLUIDO', 1),
(NOW(), 'Comum', 0.0, 0.0, 'EM_ANDAMENTO', 2),
(NOW(), 'Comum', 0.0, 0.0, 'EM_ANDAMENTO', 1);

-- Pedido 1 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1);

-- Pedido 2 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
VALUES
(1, 6, 1),
(2, 7, 1),
(3, 8, 1),
(4, 9, 1),
(5, 10, 1);

-- Pedido 3 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
VALUES
(1, 11, 1),
(2, 12, 1),
(3, 13, 1),
(4, 14, 1),
(5, 15, 1);

-- Pedido 4 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
VALUES
(1, 16, 1),
(2, 17, 1),
(3, 18, 1),
(4, 19, 1),
(5, 20, 1);

-- Pedido 5 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
VALUES
(1, 21, 1),
(2, 22, 1),
(3, 23, 1),
(4, 24, 1),
(5, 25, 1);

-- Atualizar valor total para Pedido 1
UPDATE Pedido p
SET valor_total = (
    SELECT SUM(prod.preco)
    FROM Produto prod
    JOIN Item_Pedido ip ON prod.id = ip.produto_id
    WHERE ip.pedido_id = p.id
)
WHERE p.id = 1;

-- Repetir para os demais pedidos
UPDATE Pedido p
SET valor_total = (
    SELECT SUM(prod.preco)
    FROM Produto prod
    JOIN Item_Pedido ip ON prod.id = ip.produto_id
    WHERE ip.pedido_id = p.id
)
WHERE p.id = 2;

UPDATE Pedido p
SET valor_total = (
    SELECT SUM(prod.preco)
    FROM Produto prod
    JOIN Item_Pedido ip ON prod.id = ip.produto_id
    WHERE ip.pedido_id = p.id
)
WHERE p.id = 3;

UPDATE Pedido p
SET valor_total = (
    SELECT SUM(prod.preco)
    FROM Produto prod
    JOIN Item_Pedido ip ON prod.id = ip.produto_id
    WHERE ip.pedido_id = p.id
)
WHERE p.id = 4;

UPDATE Pedido p
SET valor_total = (
    SELECT SUM(prod.preco)
    FROM Produto prod
    JOIN Item_Pedido ip ON prod.id = ip.produto_id
    WHERE ip.pedido_id = p.id
)
WHERE p.id = 5;
