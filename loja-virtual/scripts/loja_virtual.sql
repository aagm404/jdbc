create database if not exists `loja_virtual`;
use `loja_virtual`;

create table `loja_virtual`.`categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) 
  );

create table `loja_virtual`.`produto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `categoria_id` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`categoria_id`) REFERENCES categoria(id)
  );

insert into Categoria (nome) values ('Eletrodoméstico'), ('Eletrônico'), ('Computador'), ('Papelaria'), ('Móvel');

insert into Produto (nome, descricao, categoria_id) values 
('Geladeira', 'Geladeira duas portas', (select id from categoria where categoria.nome = 'Eletrodoméstico')),
('Ferro de passar', 'Ferro de passar roupas com vaporizador', (select id from categoria where categoria.nome = 'Eletrodoméstico')),
('Notebook', 'Notebook i5', (select id from categoria where categoria.nome = 'Computador'));