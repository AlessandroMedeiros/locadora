CREATE DATABASE IF NOT EXISTS locadora;

CREATE TABLE `filme` (
  `id` int(11) NOT NULL,
  `diretor` varchar(255) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO `filme` (`id`, `diretor`, `quantidade`, `titulo`) VALUES
(1, 'José Padilha', 1, 'Tropa de Elite'),
(2, 'Lilly Wachowski', 2, 'Matrix'),
(3, 'Louis Leterrier', 3, 'Carga Explosiva'),
(4, 'Justin Lin', 4, 'Velos e Furioso desafio em Tóqui'),
(5, 'Brad Bird', 5, 'Missão Impossível: Protocolo Fantasma');

CREATE TABLE `item_locacao` (
  `filme_id` int(11) NOT NULL,
  `locacao_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


INSERT INTO `item_locacao` (`filme_id`, `locacao_id`) VALUES
(1, 1),
(2, 2);

CREATE TABLE `locacao` (
  `id` int(11) NOT NULL,
  `data` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO `locacao` (`id`, `data`, `usuario_id`) VALUES
(1, '2019-10-03 14:11:00', 1),
(2, '2019-10-03 14:11:00', 2);

CREATE TABLE `perfil_model` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO `usuario` (`id`, `email`, `nome`, `senha`) VALUES
(1, 'alessandro@email.com', 'Alessandro', '$2a$10$zWQB61/1odfUFGe42t.unO/WiOFl04wPQPFSlB0xsz72cw9nfNHsm'),
(2, 'aline@email.com', 'Aline', '$2a$10$ldkv1viUjXxy.wr.LrTKYOC2cX2Nv7OMJPrXXmbFWDBuNjjWUAM7y');

CREATE TABLE `usuario_perfis` (
  `usuario_model_id` int(11) NOT NULL,
  `perfis_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


ALTER TABLE `filme`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `item_locacao`
  ADD PRIMARY KEY (`filme_id`,`locacao_id`),
  ADD KEY `FK51c8kq026c63o1l82a2fdiq8n` (`locacao_id`);

ALTER TABLE `locacao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkktfq622phc5otk6901ju9eqk` (`usuario_id`);

ALTER TABLE `perfil_model`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `usuario_perfis`
  ADD KEY `FKdi8dvwgveuvdxq5lk5ehjolqw` (`perfis_id`),
  ADD KEY `FKr8qu4r598tqirb2wkuek60sb` (`usuario_model_id`);

ALTER TABLE `filme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `locacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `perfil_model`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

