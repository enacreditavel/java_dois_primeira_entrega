package br.com.ouvidoria.atendimento.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor
@Data
public class Manifestacao {
	private String nomeUsuario;
	private String tipoManifestacao;
	private String descricao;
	private String emailUsuario;
	private int protocolo;
	
	public Manifestacao(String nomeUsuario, String tipoManifestacao, String descricao, String emailUsuario) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.tipoManifestacao = tipoManifestacao;
		this.descricao = descricao;
		this.emailUsuario = emailUsuario;
	}
	
	
}