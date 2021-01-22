package br.com.alelo.core.sftp;

public enum Sftp {

	CAMINHO_ENTRADA("/dados/INTEGRACAO/RECE/"), 
	CAMINHO_SAIDA("/dados/INTEGRACAO/EXIT/"),
	SFTP("sftp"),
	DATE_DEFAULT("MMddHHmm");

	
	private String descricao;
	
	Sftp(String descricao) {
		this.descricao = descricao;
		
	}

	public String getDescricao() {
		return descricao;
	}
	
}
