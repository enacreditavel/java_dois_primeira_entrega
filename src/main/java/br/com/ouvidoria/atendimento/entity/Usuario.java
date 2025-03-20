package br.com.ouvidoria.atendimento.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Usuario {
	private String nomeUsuario;
	private String email;
	private String senha;
	private String tipoUsuario;
	
	public Usuario(String nomeUsuario, String email, String tipoUsuario) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	

}
