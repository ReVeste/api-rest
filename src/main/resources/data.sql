
INSERT INTO Usuario (nome, cpf, telefone, email, senha, tipo, ativo)
    VALUES
    ('Matheus Rabello', '94960625040', '11992247954', 'matheus.csantos@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Pedro Saraujo', '46553098069', '13981309594', 'pedro.saraujo@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Amanda Martins', '21234567890', '11987654321', 'amanda.martins@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Ketelyn Medina', '31987654321', '11923456789', 'ketelyn.medina@sptech.schoo.', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Ryan Miranda', '31912345678', '11934567890', 'ryan.miranda@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Karen Beatriz', '31956789012', '11987651234', 'karen.beatriz@sptech.school', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'CLIENTE', true),
    ('Eduarda Procorro', '47024663057', '12920014008', 'eduardaprocorro10@gmail.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'ADMIN', true);




INSERT INTO endereco (apelido, cep, rua, numero, complemento, bairro, cidade, uf, usuario_id)
    VALUES
           ('Casa', '12345-678', 'Rua A', 123, 'Apt 101', 'Bairro A', 'Cidade A', 'SP', 1),
           ('Trabalho', '23456-789', 'Avenida B', 456, 'Sala 202', 'Bairro B', 'Cidade A', 'SP', 1),
           ('Casa', '34567-890', 'Rua C', 789, 'Casa', 'Bairro C', 'Cidade B', 'RJ', 2),
           ('Casa de Praia', '45678-901', 'Estrada D', 101, NULL, 'Bairro D', 'Cidade C', 'RJ', 2);



INSERT INTO Produto (nome, tamanho, categoria, preco, descricao, qtd_estoque, status)
VALUES
('Corpete Blood', 'P', 'ROUPA', 49.90, 'Todo com detalhes de pedrarias! Com 4 barbatanas de sustentação. O zíper dele é comprido mesmo, mas dentro tem tipo um botãozinho pra deixar ele preso e não aparecer. Condição 9/10. Medidas APROXIMADAS: Largura 66cm, busto 72cm', 1, 'DISPONIVEL'),

('Sutiã Carmilla', 'M', 'ROUPA', 42.90, 'Ele dá também pra ser usado como um top. Desapego pessoal, usado pouquinhas vezes pois não me serve bem. Alças reguláveis. Na época paguei $50 nele. Condição 9/10. Medidas APROXIMADAS: Largura no 2° fechinho (mais largo) 70cm', 1, 'DISPONIVEL'),

('Camisetão de veludo', 'G', 'ROUPA', 119.90, 'Bemmm grande! Etiqueta é tamanho único, mas é realmente bem grande. Uso G e ficou como na foto. Condição 10/10 ', 1, 'DISPONIVEL'),

('Vestido Purple', 'GG', 'ROUPA', 199.90, 'Maravilhoso, o tecido em veludo roxo é tudo. A alça dele é a mesma que faz o trançado atrás, então você pode ajustar. Da $h3in. Condição 10/10. Medidas APROXIMADAS: Minhas medidas são 94cm busto, 115 quadril e 85 cintura, ele me serviu, mas estica mais um pouquinho. Por ser ajustável atrás, veste busto um pouco maior.  ', 1, 'DISPONIVEL'),

('Vestido Moon', 'M', 'ROUPA', 79.90, 'Com detalhes nas alças e pedrarias no busto. Barra assimétrica. Condição 9/10. Medidas APROXIMADAS: Cintura 73cm até 78cm, quadril 98cm até 104cm, busto 78cm ', 1, 'DISPONIVEL'),

('Colar Moon', 'UNICO', 'ACESSORIO', 42.90, 'Novo, feito a mão!', 5, 'DISPONIVEL'),

('Blusinha Witch 1', 'G', 'ROUPA', 199.90, 'Essa barra assimétrica é o charme dela 🥹. Condição 10/10. Medidas APROXIMADAS: Busto 94cm, cintura é soltinha ', 1, 'DISPONIVEL'),

('Blusinha witch 2', 'P', 'ROUPA', 69.90, 'Mais uma no modelinho assimétrico. Condição 9/10. Medidas APROXIMADAS: Cintura 70cm, busto 72cm ', 4, 'DISPONIVEL'),

('Colar de cruz', 'UNICO', 'ACESSORIO', 31.90, 'Novo, feito a mão', 3, 'DISPONIVEL'),

('Coturno', 'M', 'ROUPA', 99.90, 'Coturno de material bem firme. Eu uso 38 mas tenho o pé magro, ficou um pouco frouxo mas nada absurdo. Os detalhezinhos são dele mesmo, não é avaria. Condição 10/10. Tamanho 38', 1, 'DISPONIVEL'),

('Baby Look Megadeth', 'M', 'ROUPA', 149.90, 'Modelo mais alongado. Cor 95%. Condição 9/10. Medidas APROXIMADAS: Largura 74cm, altura 67cm ', 1, 'DISPONIVEL'),

('Regata', 'M', 'ROUPA', 89.90, 'Condição 9/10, o fiozinho está desbotado pro azul marinho. Medidas APROXIMADAS: Largura 84cm', 1, 'DISPONIVEL'),

('Baby Look Harley Davidson', 'P', 'ROUPA', 49.90, 'ORIGINAL! Condição 9/10, desbotada nos ombros, e precisei dar um mini pontinho como mostrado nas fotos. ', 2, 'DISPONIVEL'),
--
('Brinco caveira', 'UNICO', 'ACESSORIO', 29.90, 'Novo, feito artesanalmente', 2, 'DISPONIVEL'),
('Brinco caveira 2', 'UNICO', 'ACESSORIO', 29.90, 'Novo, feito a mão', 5, 'DISPONIVEL'),
('Rosário Ankh', 'UNICO', 'ACESSORIO', 39.90, 'Novo, feito a mão', 3, 'DISPONIVEL'),
('Colar vermelho', 'UNICO', 'ACESSORIO', 42.00, 'Novo, feito a mão', 2, 'DISPONIVEL'),
('Brinco Bat', 'UNICO', 'ACESSORIO', 27.50, 'Novo, feito a mão', 3, 'DISPONIVEL'),
('Brinco Purple Goth', 'UNICO', 'ACESSORIO', 35.50, 'Novo, feito a mão', 4, 'DISPONIVEL'),
('Brinco de cruz', 'UNICO', 'ACESSORIO', 15.50, 'Novo, feito a mão', 7, 'DISPONIVEL'),
('Brinco fantasminha', 'UNICO', 'ACESSORIO', 11.00, 'Novo, feito a mão', 5, 'DISPONIVEL'),
('Brinco cabeça de bode', 'UNICO', 'ACESSORIO', 18.50, 'Novo, feito a mão', 5, 'DISPONIVEL'),
('Brinco morceguinho', 'UNICO', 'ACESSORIO', 12.50, 'Novo, feito a mão', 4, 'DISPONIVEL'),
('Chocker vermelha', 'UNICO', 'ACESSORIO', 35.50, 'Novo, feito a mão', 2, 'DISPONIVEL'),
('Brinco My Chemical Romance', 'UNICO', 'ACESSORIO', 20.00, 'Novo', 3, 'DISPONIVEL'),
('Cinto Rebite', 'UNICO', 'ACESSORIO', 34.00, 'Novo', 2, 'DISPONIVEL'),
('Corpete Carmila', 'M', 'ROUPA', 38.00, 'Condição 10/10 • Tamanho P/M • alças ajustáveis • fechamento em colchetes', 1, 'DISPONIVEL'),
('Vestido de renda', 'P', 'ROUPA', 32.90, 'Condição 9/10, fios puxados embaixo zíper na lateral •NÃO acompanha cinto. Medidas APROXIMADAS: Cintura 66cm, 78cm busto, 90cm altura ', 1, 'DISPONIVEL'),
('Jaqueta Elvira', 'M', 'ROUPA', 58.00, 'Couro legítimo • peça CGC (no mínimo 20 anos de existência!!) • modelo acinturado • condição 9/10, está faltando um botão ', 1, 'DISPONIVEL'),
('Jaqueta Spikes', 'P', 'ROUPA', 40.00, 'Condição 9/10 • com detalhes em spikes, perfeita', 1, 'DISPONIVEL');



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
    (1, 'https://i.imgur.com/jSvavfD.jpeg'),
    (1, 'https://i.imgur.com/AJiQSmG.jpeg'),
    (1, 'https://i.imgur.com/9NHxkg8.jpeg'),
    (1, 'https://i.imgur.com/Bm35yhP.jpeg'),

    (2, 'https://i.imgur.com/ekxzA7N.jpeg'),
    (2, 'https://i.imgur.com/197gOaA.jpeg'),
    (2, 'https://i.imgur.com/14kanZv.jpeg'),
    (2, 'https://i.imgur.com/kA2DqKF.jpeg'),

    (3, 'https://i.imgur.com/9tl90Qk.jpeg'),
    (3, 'https://i.imgur.com/SK2x2b6.jpeg'),
    (3, 'https://i.imgur.com/o65mjNu.jpeg'),
    (3, 'https://i.imgur.com/ebyMquq.jpeg'),

    (4, 'https://i.imgur.com/KnWhmMy.jpeg'),
    (4, 'https://i.imgur.com/mQNa326.jpeg'),
    (4, 'https://i.imgur.com/gQaIHhp.jpeg'),

    (5, 'https://i.imgur.com/th1Be5i.jpeg'),
    (5, 'https://i.imgur.com/4bvVeTz.jpeg'),
    (5, 'https://i.imgur.com/3KPBrst.jpeg'),

    (6, 'https://i.imgur.com/XvkFQKH.jpeg'),

    (7, 'https://i.imgur.com/TB5igvb.jpeg'),
    (7, 'https://i.imgur.com/JPBwTB4.jpeg'),

    (8, 'https://i.imgur.com/tyc16ch.jpeg'),
    (8, 'https://i.imgur.com/CyksGfB.jpeg'),

    (9, 'https://i.imgur.com/vd0slxO.jpeg'),

    (10, 'https://i.imgur.com/XwQm1KY.jpeg'),
    (10, 'https://i.imgur.com/sHcfoOm.jpeg'),
    (10, 'https://i.imgur.com/Kxp4ErN.jpeg'),
    (10, 'https://i.imgur.com/vxXN9OM.jpeg'),

    (11, 'https://i.imgur.com/630PZWK.jpeg'),
    (11, 'https://i.imgur.com/XIf4A9z.jpeg'),
    (11, 'https://i.imgur.com/wNiknnF.jpeg'),

    (12, 'https://i.imgur.com/VSfZioB.jpeg'),
    (12, 'https://i.imgur.com/eLMNDed.jpeg'),
    (12, 'https://i.imgur.com/dQMRdb8.jpeg'),
    (12, 'https://i.imgur.com/tmxp9OP.jpeg'),

    (13, 'https://i.imgur.com/irxBIFk.jpeg'),
    (13, 'https://i.imgur.com/eghcMW9.jpeg'),
    (13, 'https://i.imgur.com/YetWLX1.jpeg'),
    (13, 'https://i.imgur.com/vryRqtV.jpeg'),

    (14, 'https://i.imgur.com/txFAGFv.jpeg'),
    (14, 'https://i.imgur.com/oRhHURe.jpeg'),

    (15, 'https://i.imgur.com/paSUi2i.jpeg'),
    (15, 'https://i.imgur.com/E5LhTra.jpeg'),

    (16, 'https://i.imgur.com/Ms4T9Yn.jpeg'),

    (17, 'https://i.imgur.com/hTeTfI7.jpeg'),
    (17, 'https://i.imgur.com/QgwcY6O.jpeg'),
    (17, 'https://i.imgur.com/gsnUE0C.jpeg'),

    (18, 'https://i.imgur.com/eeWsO5m.jpeg'),

    (19, 'https://i.imgur.com/daYqrLy.jpeg'),
    (19, 'https://i.imgur.com/ExNIoTf.jpeg'),

    (20, 'https://i.imgur.com/fRJRkoO.jpeg'),
    (20, 'https://i.imgur.com/4s3zIq3.jpeg'),

    (21, 'https://i.imgur.com/QDRKKdM.jpeg'),
    (21, 'https://i.imgur.com/HSMW25x.jpeg'),

    (22, 'https://i.imgur.com/7aNZloT.jpeg'),
    (22, 'https://i.imgur.com/vQFhult.jpeg'),

    (23, 'https://i.imgur.com/e6piOrE.jpeg'),
    (23, 'https://i.imgur.com/0DOUNtv.jpeg'),
    (23, 'https://i.imgur.com/EoG9UKa.jpeg'),

    (24, 'https://i.imgur.com/SSdB2BH.jpeg'),
    (24, 'https://i.imgur.com/uc4ckeG.jpeg'),
    (24, 'https://i.imgur.com/bvO8NNu.jpeg'),
    (24, 'https://i.imgur.com/snQtNaN.jpeg'),

    (25, 'https://i.imgur.com/Yt5vSk9.jpeg'),
    (25, 'https://i.imgur.com/Q3xcdSG.jpeg'),
    (25, 'https://i.imgur.com/NGuiUyQ.jpeg'),

    (26, 'https://i.imgur.com/6BtYGpR.jpeg'),
    (26, 'https://i.imgur.com/u5uhdsF.jpeg'),

    (27, 'https://i.imgur.com/B3imsJ2.jpeg'),
    (27, 'https://i.imgur.com/TmVRGgm.jpeg'),
    (27, 'https://i.imgur.com/fSULAgW.jpeg'),
    (27, 'https://i.imgur.com/E1mFs3h.jpeg'),

    (28, 'https://i.imgur.com/OZfNUBw.jpeg'),
    (28, 'https://i.imgur.com/JUDqpX0.jpeg'),
    (28, 'https://i.imgur.com/aN3YPRO.jpeg'),
    (28, 'https://i.imgur.com/nYu55ME.jpeg'),

    (29, 'https://i.imgur.com/Gn5qb3r.jpeg'),
    (29, 'https://i.imgur.com/X6mr7ZA.jpeg'),
    (29, 'https://i.imgur.com/oCoGgkV.jpeg'),
    (29, 'https://i.imgur.com/bqbewsV.jpeg'),

    (30, 'https://i.imgur.com/QQP3pjt.jpeg'),
    (30, 'https://i.imgur.com/foBlz0A.jpeg'),
    (30, 'https://i.imgur.com/dqCXav7.jpeg'),
    (30, 'https://i.imgur.com/VZjwgDG.jpeg');




INSERT INTO Pedido (data_hora, tipo_frete, valor_frete, valor_total, status, usuario_id)
VALUES
(NOW(), 'Comum', 0.0, 0.0, 'CONCLUIDO', 1),
(NOW(), 'Comum', 0.0, 0.0, 'EM_ANDAMENTO', 2),
(NOW(), 'Comum', 0.0, 0.0, 'EM_ANDAMENTO', 1);

-- Pedido 1 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1);

-- Pedido 2 com 5 produtos
INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
VALUES
(1, 6, 1),
(2, 7, 1),
(3, 8, 1);

-- Pedido 3 com 5 produtos
--INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
--VALUES
--(1, 11, 1),
--(2, 12, 1),
--(3, 13, 1),
--(4, 14, 1);
--(5, 15, 1);

-- Pedido 4 com 5 produtos
--INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
--VALUES
--(1, 16, 1),
--(2, 17, 1),
--(3, 18, 1),
--(4, 19, 1),
--(5, 20, 1);

-- Pedido 5 com 5 produtos
--INSERT INTO Item_Pedido (pedido_id, produto_id, quantidade)
--VALUES
--(1, 21, 1),
--(2, 22, 1),
--(3, 23, 1),
--(4, 24, 1),
--(5, 25, 1);

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

--UPDATE Pedido p
--SET valor_total = (
--    SELECT SUM(prod.preco)
--    FROM Produto prod
--    JOIN Item_Pedido ip ON prod.id = ip.produto_id
--    WHERE ip.pedido_id = p.id
--)
--WHERE p.id = 4;
--
--UPDATE Pedido p
--SET valor_total = (
--    SELECT SUM(prod.preco)
--    FROM Produto prod
--    JOIN Item_Pedido ip ON prod.id = ip.produto_id
--    WHERE ip.pedido_id = p.id
--)
--WHERE p.id = 5;