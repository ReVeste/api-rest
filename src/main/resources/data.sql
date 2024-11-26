
INSERT INTO Usuario (nome, cpf, telefone, email, senha, tipo, ativo)
    VALUES
    ('Matheus Rabello', '94960625040', '11992247954', 'matheus.csantos@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Pedro Saraujo', '46553098069', '13981309594', 'pedro.saraujo@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Amanda Martins', '21234567890', '11987654321', 'amanda.martins@example.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Ketelyn Medina', '31987654321', '11923456789', 'ketelyn.medina@example.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Ryan Miranda', '31912345678', '11934567890', 'ryan.miranda@example.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Karen Beatriz', '31956789012', '11987651234', 'karen.beatriz@example.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true);



INSERT INTO endereco (apelido, cep, rua, numero, complemento, bairro, cidade, uf, usuario_id)
    VALUES
           ('Casa', '12345-678', 'Rua A', 123, 'Apt 101', 'Bairro A', 'Cidade A', 'SP', 1),
           ('Trabalho', '23456-789', 'Avenida B', 456, 'Sala 202', 'Bairro B', 'Cidade A', 'SP', 1),
           ('Casa', '34567-890', 'Rua C', 789, 'Casa', 'Bairro C', 'Cidade B', 'RJ', 2),
           ('Casa de Praia', '45678-901', 'Estrada D', 101, NULL, 'Bairro D', 'Cidade C', 'RJ', 2);


INSERT INTO Produto (nome, tamanho, categoria, preco, descricao, qtd_estoque, status)
VALUES
('Camiseta Básica Branca', 'M', 'ROUPA', 49.90, 'Camiseta de algodão básica branca', 1, 'DISPONIVEL'),
('Calça Jeans Slim', 'G', 'ROUPA', 89.90, 'Calça jeans modelo slim fit', 2, 'DISPONIVEL'),
('Vestido Midi Estampado', 'P', 'ROUPA', 119.90, 'Vestido midi com estampa floral', 1, 'DISPONIVEL'),
('Jaqueta Jeans', 'GG', 'ROUPA', 199.90, 'Jaqueta jeans clássica', 1, 'RESERVADO'),
('Saia Lápis Preta', 'M', 'ROUPA', 79.90, 'Saia lápis preta ajustada', 2, 'DISPONIVEL'),
('Camiseta Estampada Vintage', 'P', 'ROUPA', 59.90, 'Camiseta com estampa vintage retrô', 3, 'DISPONIVEL'),
('Cardigan de Tricô', 'GG', 'ROUPA', 199.90, 'Cardigan de tricô confortável e quentinho', 1, 'RESERVADO'),
('Blusa Canelada', 'PP', 'ROUPA', 69.90, 'Blusa canelada com gola alta', 4, 'DISPONIVEL'),
('Calça Legging', 'M', 'ROUPA', 59.90, 'Calça legging preta esportiva', 3, 'DISPONIVEL'),
('Camisa Social Listrada', 'G', 'ROUPA', 99.90, 'Camisa social listrada azul', 2, 'DISPONIVEL'),
('Blusa de Frio com Capuz', 'GG', 'ROUPA', 149.90, 'Blusa de frio com capuz e bolso frontal', 1, 'RESERVADO'),
('Colete de Nylon', 'M', 'ROUPA', 89.90, 'Colete leve de nylon para meia estação', 1, 'DISPONIVEL'),
('Blusa Cropped', 'P', 'ROUPA', 49.90, 'Blusa cropped de algodão', 2, 'DISPONIVEL'),
('Cinto de Couro Preto', 'UNICO', 'ACESSORIO', 69.90, 'Cinto de couro preto com fivela dourada', 2, 'RESERVADO'),
('Gorro de Lã Cinza', 'UNICO', 'ACESSORIO', 39.90, 'Gorro de lã cinza ideal para o inverno', 5, 'DISPONIVEL'),
('Cachecol de Tricô', 'UNICO', 'ACESSORIO', 49.90, 'Cachecol de tricô com franjas nas pontas', 3, 'DISPONIVEL'),
('Pulseira de Couro', 'UNICO', 'ACESSORIO', 29.90, 'Pulseira de couro com detalhe em metal', 4, 'DISPONIVEL'),
('Relógio Minimalista', 'UNICO', 'ACESSORIO', 299.90, 'Relógio minimalista com pulseira de couro', 1, 'VENDIDO'),
('Bolsa Tiracolo', 'UNICO', 'ACESSORIO', 149.90, 'Bolsa tiracolo em couro sintético', 1, 'RESERVADO'),
('Óculos de Sol Redondo', 'UNICO', 'ACESSORIO', 99.90, 'Óculos de sol redondo estilo retrô', 3, 'DISPONIVEL'),
('Camisa Polo Básica', 'M', 'ROUPA', 79.90, 'Camisa polo básica com gola tradicional', 2, 'DISPONIVEL'),
('Blazer Slim Fit', 'G', 'ROUPA', 199.90, 'Blazer slim fit para ocasiões formais', 1, 'RESERVADO'),
('Calça de Sarja', 'GG', 'ROUPA', 89.90, 'Calça de sarja caqui ajustada', 2, 'DISPONIVEL'),
('Jaqueta de Sarja', 'P', 'ROUPA', 189.90, 'Jaqueta de sarja com bolso frontal', 1, 'DISPONIVEL'),
('Cinto Trançado', 'UNICO', 'ACESSORIO', 59.90, 'Cinto trançado com fivela metálica', 2, 'DISPONIVEL'),
('Brinco de Argola', 'UNICO', 'ACESSORIO', 19.90, 'Brinco de argola dourada', 6, 'DISPONIVEL'),
('Boné Estampado', 'UNICO', 'ACESSORIO', 49.90, 'Boné com estampa geométrica', 2, 'DISPONIVEL'),
('Mochila de Couro', 'UNICO', 'ACESSORIO', 199.90, 'Mochila de couro sintético marrom', 1, 'RESERVADO'),
('Bermuda Cargo', 'G', 'ROUPA', 69.90, 'Bermuda cargo com bolsos laterais', 3, 'DISPONIVEL'),
('Blusa de Manga Longa', 'GG', 'ROUPA', 79.90, 'Blusa de manga longa em algodão leve', 1, 'DISPONIVEL');



INSERT INTO feedback (nota, comentario, usuario_id) VALUES
(5, 'Perfeito! Entrega rápida, produto de alta qualidade.', 1),
(4, 'A entrega podia ser melhor.', 2),
(3, 'O produto é ok, mas esperava algo diferente.', 3),
(4, 'Produto bom, mas o suporte poderia ser mais rápido.', 4),
(5, 'Excelente! Superou as expectativas!', 5),
(2, 'Produto deixou a desejar, não atende às necessidades.', 1),
(5, 'Ótimo atendimento e peças bem cuidadas!', 2);



INSERT INTO Imagem (produto_id, imagem_url)
VALUES
    (1, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (1, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (1, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (1, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (2, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (2, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (2, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (2, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (3, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (3, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (3, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (3, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (4, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (4, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (4, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (4, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (5, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (5, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (5, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (5, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (6, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (6, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (6, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (6, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (7, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (7, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (7, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (7, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (8, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (8, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (8, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (8, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (9, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (9, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (9, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (9, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (10, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (10, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (10, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (10, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (11, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (11, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (11, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (11, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (12, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (12, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (12, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (12, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (13, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (13, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (13, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (13, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (14, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (14, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (14, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (14, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (15, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (15, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (15, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (15, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (16, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (16, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (16, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (16, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (17, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (17, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (17, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (17, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (18, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (18, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (18, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (18, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (19, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (19, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (19, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (19, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (20, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (20, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (20, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (20, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (21, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (21, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (21, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (21, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (22, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (22, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (22, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (22, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (23, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (23, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (23, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (23, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (24, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (24, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (24, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (24, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (25, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (25, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (25, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (25, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (26, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (26, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (26, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (26, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (27, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (27, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (27, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (27, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (28, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (28, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (28, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (28, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (29, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (29, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (29, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (29, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (30, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (30, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (30, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg'),
    (30, 'https://i.pinimg.com/originals/aa/52/90/aa5290929d03f73923e94fdc0d3e5897.jpg');




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