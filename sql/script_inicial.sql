create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data datetime, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora integer, id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data datetime, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora integer, id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora integer, id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora integer, id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data date, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora varchar(255), id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
create table horario (id integer not null auto_increment, hora varchar(255) not null, primary key (id)) engine=MyISAM
create table reserva (id integer not null auto_increment, data datetime not null, id_horario integer not null, id_reuniao integer not null, id_sala integer not null, primary key (id)) engine=MyISAM
create table reuniao (id integer not null auto_increment, data_criacao datetime, extra_agua bit not null, extra_biscoito bit not null, extra_cafe bit not null, id_solicitante integer not null, impressora bit not null, projetor bit not null, publico varchar(255) not null, quantidade_notebooks integer not null, quantidade_pessoas integer not null, responsavel varchar(255) not null, tema varchar(255) not null, primary key (id)) engine=MyISAM
create table sala (id integer not null auto_increment, andar varchar(255) not null, computador bit not null, impressora bit not null, nome varchar(255) not null, quantidade_pessoas integer not null, primary key (id)) engine=MyISAM
alter table horario add constraint UK4sbcafqin6ocv4636j6bu9l7i unique (hora)
alter table reserva add constraint UKssyslmbuhe12buwh93f4ujh45 unique (data, id_sala, id_horario)
alter table sala add constraint UKnib1y0nf537axkjoiujs2rcv5 unique (nome)
alter table reserva add constraint FKgjiki3f79moop3i07l68xwv0n foreign key (id_horario) references horario (id)
alter table reserva add constraint FKjjkdfbapa0kseodvg3udrjnnr foreign key (id_reuniao) references reuniao (id)
alter table reserva add constraint FKq8aw9okjkra5d3nyvymrcg9sb foreign key (id_sala) references sala (id)
