package br.org.cremesp.agenda.sala.constantes;

public enum AgendamentoEnum {
	
	MSG_AGENDAMENTO("AGENDAMENTO"),
	MSG_SUCESSO("SUCESSO"),
	MSG_ERRO("ERRO")

	;
	
	private String texto;

    private AgendamentoEnum(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
