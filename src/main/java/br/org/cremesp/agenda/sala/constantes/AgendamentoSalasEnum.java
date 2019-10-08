package br.org.cremesp.agenda.sala.constantes;

public enum AgendamentoSalasEnum {
	
	MSG_HORARIO_FIND_ERRO("Hora não encontrada!"),
	MSG_HORARIO_SAVE_ERRO("A hora não foi salva!"),
	MSG_HORARIO_UPDATE_ERRO("A hora não foi atualizada!"),
	MSG_HORARIO_DELETE_ERRO("A hora não foi excluída!"),
	
	MSG_RESERVA_FIND_ERRO("Reserva não encontrada!"),
	MSG_RESERVA_SAVE_ERRO("A reserva não foi salva!"),
	MSG_RESERVA_SAVE_CONSTRAINT_ERRO("A reserva não foi salva, pois já existe uma reserva com essa data, sala e horário!"),
	MSG_RESERVA_UPDATE_ERRO("A reserva não foi atualizada!"),
	MSG_RESERVA_UPDATE_CONSTRAINT_ERRO("A reserva não foi atualizada, pois já existe uma reserva com essa data, sala e horário!"),
	MSG_RESERVA_DELETE_ERRO("A reserva não foi excluída!"),
	
	MSG_REUNIAO_FIND_ERRO("Reunião não encontrada!"),
	MSG_REUNIAO_SAVE_ERRO("A reunião não foi salva!"),
	MSG_REUNIAO_SAVE_CONSTRAINT_ERRO("A reunião não foi salva, pois já existe uma reunião com esse tema para o solicitante!"),
	MSG_REUNIAO_UPDATE_ERRO("A reunião não foi atualizada!"),
	MSG_REUNIAO_UPDATE_CONSTRAINT_ERRO("A reunião não foi atualizada, pois já existe uma reunião com esse tema para o solicitante!"),
	MSG_REUNIAO_DELETE_ERRO("A reunião não foi excluída!"),
	
	MSG_SALA_FIND_ERRO("Sala não encontrada!"),
	MSG_SALA_SAVE_ERRO("A sala não foi salva!"),
	MSG_SALA_UPDATE_ERRO("A sala não foi atualizada!"),
	MSG_SALA_DELETE_ERRO("A sala não foi excluída!")	
	
	;
	
	private String texto;

    private AgendamentoSalasEnum(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
