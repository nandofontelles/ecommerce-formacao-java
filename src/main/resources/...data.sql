-- =====================================================
-- Massa de dados mockada - tabela product (H2)
-- Total: 52 produtos
-- =====================================================

-- ELECTRONICS (9 produtos)
insert into product (id, name, category, price, state, description) values (1, 'Smartphone Galaxy Nova X', 'ELECTRONICS', 2899.90, 'ATIVO', 'Smartphone com tela AMOLED de 6.5 polegadas, 256GB de armazenamento e camera tripla de 108MP');
insert into product (id, name, category, price, state, description) values (2, 'Notebook UltraBook Pro 14', 'ELECTRONICS', 5499.00, 'ATIVO', 'Notebook com processador de 8 nucleos, 16GB de RAM, SSD de 512GB e tela Full HD de 14 polegadas');
insert into product (id, name, category, price, state, description) values (3, 'Fone de Ouvido Bluetooth SoundMax', 'ELECTRONICS', 349.90, 'ATIVO', 'Fone over-ear com cancelamento de ruido ativo e bateria de ate 30 horas');
insert into product (id, name, category, price, state, description) values (4, 'Smart TV 55 4K VisionPlus', 'ELECTRONICS', 3199.00, 'ATIVO', 'Smart TV LED 55 polegadas com resolucao 4K, HDR10 e sistema com apps de streaming integrados');
insert into product (id, name, category, price, state, description) values (5, 'Smartwatch FitPulse 3', 'ELECTRONICS', 599.90, 'INATIVO', 'Relogio inteligente com monitor cardiaco, GPS integrado e resistencia a agua 5ATM');
insert into product (id, name, category, price, state, description) values (6, 'Tablet TabView 10', 'ELECTRONICS', 1499.00, 'ATIVO', 'Tablet com tela de 10.4 polegadas, 128GB de armazenamento e suporte a caneta stylus');
insert into product (id, name, category, price, state, description) values (7, 'Caixa de Som Portatil BoomBox Go', 'ELECTRONICS', 279.90, 'ATIVO', 'Caixa de som bluetooth a prova de agua com 20W de potencia e bateria de 12 horas');
insert into product (id, name, category, price, state, description) values (8, 'Carregador Turbo USB-C 65W', 'ELECTRONICS', 159.90, 'ATIVO', 'Carregador de parede com carregamento rapido compativel com notebooks, tablets e smartphones');
insert into product (id, name, category, price, state, description) values (9, 'Console GameStation Mini', 'ELECTRONICS', 2299.00, 'INATIVO', 'Console de videogame compacto com 1TB de armazenamento e controle sem fio incluso');

-- CLOTHING (7 produtos)
insert into product (id, name, category, price, state, description) values (10, 'Camiseta Basica Algodao', 'CLOTHING', 49.90, 'ATIVO', 'Camiseta masculina gola redonda 100% algodao, disponivel em diversas cores');
insert into product (id, name, category, price, state, description) values (11, 'Calca Jeans Slim Fit', 'CLOTHING', 159.90, 'ATIVO', 'Calca jeans com elastano, modelagem slim e lavagem escura');
insert into product (id, name, category, price, state, description) values (12, 'Vestido Midi Floral', 'CLOTHING', 189.90, 'ATIVO', 'Vestido feminino midi em viscose com estampa floral e alcas ajustaveis');
insert into product (id, name, category, price, state, description) values (13, 'Jaqueta Corta-Vento Urban', 'CLOTHING', 229.90, 'INATIVO', 'Jaqueta leve impermeavel com capuz e bolsos com ziper');
insert into product (id, name, category, price, state, description) values (14, 'Moletom Canguru Unissex', 'CLOTHING', 139.90, 'ATIVO', 'Moletom com capuz e bolso frontal, interior flanelado');
insert into product (id, name, category, price, state, description) values (15, 'Camisa Social Manga Longa', 'CLOTHING', 129.90, 'ATIVO', 'Camisa social masculina em tricoline com modelagem regular');
insert into product (id, name, category, price, state, description) values (16, 'Bermuda Sarja Casual', 'CLOTHING', 89.90, 'ATIVO', 'Bermuda masculina em sarja com cinco bolsos e cós com passantes');

-- HOME_GARDEN (8 produtos)
insert into product (id, name, category, price, state, description) values (17, 'Cadeira Escritorio Ergonomica', 'HOME_GARDEN', 899.00, 'ATIVO', 'Cadeira com apoio lombar ajustavel, bracos reguláveis e encosto em tela respiravel');
insert into product (id, name, category, price, state, description) values (18, 'Jogo de Panelas Antiaderente 5 Pecas', 'HOME_GARDEN', 399.90, 'ATIVO', 'Conjunto de panelas com revestimento antiaderente e tampas de vidro temperado');
insert into product (id, name, category, price, state, description) values (19, 'Mangueira Jardim 20 Metros', 'HOME_GARDEN', 119.90, 'ATIVO', 'Mangueira flexivel com esguicho de 7 funcoes e engates rapidos');
insert into product (id, name, category, price, state, description) values (20, 'Kit Vasos Ceramica Decorativos', 'HOME_GARDEN', 149.90, 'INATIVO', 'Conjunto com 3 vasos de ceramica em tamanhos variados para plantas de interior');
insert into product (id, name, category, price, state, description) values (21, 'Luminaria de Mesa LED', 'HOME_GARDEN', 89.90, 'ATIVO', 'Luminaria articulada com 3 niveis de intensidade e luz de baixo consumo');
insert into product (id, name, category, price, state, description) values (22, 'Jogo de Cama Queen 4 Pecas', 'HOME_GARDEN', 249.90, 'ATIVO', 'Jogo de cama em percal 200 fios com lencol, elastico e duas fronhas');
insert into product (id, name, category, price, state, description) values (23, 'Tesoura de Poda Profissional', 'HOME_GARDEN', 79.90, 'ATIVO', 'Tesoura de poda com laminas de aco carbono e cabo emborrachado antiderrapante');
insert into product (id, name, category, price, state, description) values (24, 'Mesa de Centro Madeira Rustica', 'HOME_GARDEN', 599.00, 'ATIVO', 'Mesa de centro em madeira macica com acabamento rustico e prateleira inferior');

-- TOYS (5 produtos)
insert into product (id, name, category, price, state, description) values (25, 'Quebra-Cabeca 1000 Pecas Paisagem', 'TOYS', 69.90, 'ATIVO', 'Quebra-cabeca com imagem de paisagem natural, indicado para maiores de 12 anos');
insert into product (id, name, category, price, state, description) values (26, 'Kit Blocos de Montar 500 Pecas', 'TOYS', 149.90, 'ATIVO', 'Blocos de encaixe coloridos que estimulam a criatividade e coordenacao motora');
insert into product (id, name, category, price, state, description) values (27, 'Carrinho Controle Remoto Turbo', 'TOYS', 199.90, 'ATIVO', 'Carrinho off-road com controle remoto 2.4GHz e bateria recarregavel');
insert into product (id, name, category, price, state, description) values (28, 'Boneca Articulada Aventureira', 'TOYS', 89.90, 'INATIVO', 'Boneca com articulacoes moveis e acessorios de acampamento inclusos');
insert into product (id, name, category, price, state, description) values (29, 'Jogo de Tabuleiro Estrategia', 'TOYS', 119.90, 'ATIVO', 'Jogo de tabuleiro para 2 a 6 jogadores com foco em estrategia e negociacao');

-- SPORTS (10 produtos)
insert into product (id, name, category, price, state, description) values (30, 'Tenis de Corrida AirRun', 'SPORTS', 399.90, 'ATIVO', 'Tenis com amortecimento em espuma e cabedal em mesh respiravel para corridas longas');
insert into product (id, name, category, price, state, description) values (31, 'Bola de Futebol Campo Oficial', 'SPORTS', 149.90, 'ATIVO', 'Bola de futebol de campo tamanho 5 com costura termossoldada');
insert into product (id, name, category, price, state, description) values (32, 'Kit Halteres Ajustaveis 20kg', 'SPORTS', 349.90, 'ATIVO', 'Par de halteres com anilhas removiveis e barras com pegada emborrachada');
insert into product (id, name, category, price, state, description) values (33, 'Tapete de Yoga Antiderrapante', 'SPORTS', 99.90, 'ATIVO', 'Tapete de yoga em TPE com 6mm de espessura e alca para transporte');
insert into product (id, name, category, price, state, description) values (34, 'Bicicleta Aro 29 Mountain', 'SPORTS', 1899.00, 'ATIVO', 'Mountain bike com quadro de aluminio, 21 marchas e freio a disco');
insert into product (id, name, category, price, state, description) values (35, 'Raquete de Tenis ProSpin', 'SPORTS', 459.90, 'INATIVO', 'Raquete de tenis em grafite, peso de 300g e cabeca de 100 polegadas quadradas');
insert into product (id, name, category, price, state, description) values (36, 'Corda de Pular Speed', 'SPORTS', 39.90, 'ATIVO', 'Corda de pular com rolamentos e cabo de aco ajustavel para treinos de alta intensidade');
insert into product (id, name, category, price, state, description) values (37, 'Luvas de Boxe 12oz', 'SPORTS', 189.90, 'ATIVO', 'Luvas de boxe em couro sintetico com enchimento de espuma e fechamento em velcro');
insert into product (id, name, category, price, state, description) values (38, 'Garrafa Termica Esportiva 1L', 'SPORTS', 79.90, 'ATIVO', 'Garrafa em aco inox que mantem a temperatura por ate 12 horas');
insert into product (id, name, category, price, state, description) values (39, 'Oculos de Natacao AquaView', 'SPORTS', 59.90, 'INATIVO', 'Oculos de natacao com lentes antiembacantes e protecao UV');

-- BEAUTY (6 produtos)
insert into product (id, name, category, price, state, description) values (40, 'Secador de Cabelo Ionico 2000W', 'BEAUTY', 229.90, 'ATIVO', 'Secador com tecnologia ionica, 3 temperaturas e 2 velocidades');
insert into product (id, name, category, price, state, description) values (41, 'Kit Skincare Hidratacao Facial', 'BEAUTY', 179.90, 'ATIVO', 'Kit com sabonete facial, tonico e hidratante com acido hialuronico');
insert into product (id, name, category, price, state, description) values (42, 'Paleta de Sombras 18 Cores', 'BEAUTY', 89.90, 'ATIVO', 'Paleta de sombras com acabamentos matte e cintilante de alta pigmentacao');
insert into product (id, name, category, price, state, description) values (43, 'Perfume Floral 100ml', 'BEAUTY', 279.90, 'INATIVO', 'Eau de parfum feminino com notas florais e fundo amadeirado');
insert into product (id, name, category, price, state, description) values (44, 'Prancha Alisadora Ceramica', 'BEAUTY', 199.90, 'ATIVO', 'Prancha com placas de ceramica e controle digital de temperatura ate 230 graus');
insert into product (id, name, category, price, state, description) values (45, 'Protetor Solar Facial FPS 50', 'BEAUTY', 69.90, 'ATIVO', 'Protetor solar facial com toque seco e alta protecao UVA e UVB');

-- AUTOMOTIVE (7 produtos)
insert into product (id, name, category, price, state, description) values (46, 'Kit Tapetes Automotivos Borracha', 'AUTOMOTIVE', 129.90, 'ATIVO', 'Jogo de tapetes universais em borracha com 4 pecas e bordas elevadas');
insert into product (id, name, category, price, state, description) values (47, 'Calibrador de Pneus Digital', 'AUTOMOTIVE', 89.90, 'ATIVO', 'Calibrador portatil com visor digital e leitura em PSI e BAR');
insert into product (id, name, category, price, state, description) values (48, 'Aspirador de Po Automotivo 12V', 'AUTOMOTIVE', 149.90, 'ATIVO', 'Aspirador portatil conectado ao acendedor do carro com filtro lavavel');
insert into product (id, name, category, price, state, description) values (49, 'Suporte Veicular para Celular', 'AUTOMOTIVE', 49.90, 'ATIVO', 'Suporte magnetico com fixacao na saida de ar e rotacao de 360 graus');
insert into product (id, name, category, price, state, description) values (50, 'Oleo de Motor Sintetico 5W30 1L', 'AUTOMOTIVE', 54.90, 'ATIVO', 'Oleo lubrificante sintetico para motores a gasolina, etanol e flex');
insert into product (id, name, category, price, state, description) values (51, 'Camera de Re com Sensor', 'AUTOMOTIVE', 259.90, 'INATIVO', 'Camera de re com visao noturna e sensor de estacionamento com alerta sonoro');
insert into product (id, name, category, price, state, description) values (52, 'Capa Protetora para Carro', 'AUTOMOTIVE', 199.90, 'ATIVO', 'Capa impermeavel com forro interno e protecao contra raios UV');

ALTER SEQUENCE PRODUCT_SEQ RESTART WITH 53;