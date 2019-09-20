package br.org.cremesp.agenda.sala.constantes;

public enum PublicoEnum {
	
	INTERNO("INTERNO"),
	EXTERNO("EXTERNO")

	;
	
	private String texto;

    private PublicoEnum(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
