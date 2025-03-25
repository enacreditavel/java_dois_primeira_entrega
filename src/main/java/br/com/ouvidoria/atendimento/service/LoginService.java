package br.com.ouvidoria.atendimento.service;

import br.com.ouvidoria.atendimento.entity.Usuario;
import br.com.ouvidoria.atendimento.util.Tratamentos;

public class LoginService {
	// private ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
	Tratamentos tratarVariavel = new Tratamentos();

	public Usuario login() {
		Usuario utilizador = null;
		// login

		do {
			String nomeUser = tratarVariavel.tratamentosStringNull("Nome: ", "Informe seu nome");
			
			if (nomeUser == null) {
				return utilizador;
			}

			

			String emailUser = tratarVariavel.tratamentosStringNull("Email: ", "Informe seu email");

			if (emailUser != null && nomeUser != null) {
				utilizador = new Usuario(nomeUser, emailUser);
				return utilizador;

			} 

		} while (utilizador == null);

		return utilizador;

	}

}
