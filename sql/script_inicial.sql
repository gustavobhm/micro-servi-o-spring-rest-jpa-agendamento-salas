create table horario (id integer not null auto_increment, hora varchar(255) not null, primary key (id)) engine=MyISAM
create table reserva (id integer not null auto_increment, cafe bit not null, data datetime not null, id_horario integer not null, id_reuniao integer not null, id_sala integer not null, primary key (id)) engine=MyISAM
create table reuniao (id integer not null auto_increment, data_criacao datetime, extra_agua bit not null, extra_biscoito bit not null, extra_cafe bit not null, id_solicitante integer not null, impressora bit not null, projetor bit not null, publico varchar(255) not null, quantidade_notebooks integer not null, quantidade_pessoas integer not null, responsavel varchar(255) not null, tema varchar(255) not null, primary key (id)) engine=MyISAM
create table sala (id integer not null auto_increment, andar varchar(255) not null, ativa bit not null, computador bit not null, impressora bit not null, nome varchar(255) not null, quantidade_pessoas integer not null, primary key (id)) engine=MyISAM
alter table horario add constraint UK4sbcafqin6ocv4636j6bu9l7i unique (hora)
alter table reserva add constraint UKssyslmbuhe12buwh93f4ujh45 unique (data, id_sala, id_horario)
alter table sala add constraint UKnib1y0nf537axkjoiujs2rcv5 unique (nome)
alter table reserva add constraint FKgjiki3f79moop3i07l68xwv0n foreign key (id_horario) references horario (id)
alter table reserva add constraint FKjjkdfbapa0kseodvg3udrjnnr foreign key (id_reuniao) references reuniao (id)
alter table reserva add constraint FKq8aw9okjkra5d3nyvymrcg9sb foreign key (id_sala) references sala (id)
