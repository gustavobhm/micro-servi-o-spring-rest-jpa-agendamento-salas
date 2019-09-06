package br.org.cremesp.agenda.sala.constantes;

public enum AgendamentoSalasEnum {
	
	MSG_AGENDAMENTO("AGENDAMENTO"),
	MSG_SUCESSO("SUCESSO"),
	MSG_ERRO("ERRO")

	;
	
	private String texto;

    private AgendamentoSalasEnum(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
