package br.com.ouvidoria.atendimento.server;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.ouvidoria.atendimento.entity.ObjetosMenus;
import br.com.ouvidoria.atendimento.entity.Usuario;
import br.com.ouvidoria.atendimento.services.UsuarioService;

public class ServerLogin extends Servidor {
	// private ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
	UsuarioService usuarioDAO = new UsuarioService();
	Tratamentos tratarVariavel = new Tratamentos();

	public void adicionarNovoUsuario() {
		String nomeUsuario = tratarVariavel.tratamentosString("Nome do Usuário", "Digite o nome do novo usuário");
		String email = tratarVariavel.tratamentosString("Email: ", "Digite o email do novo usuário: ");
		String senha = tratarVariavel.tratamentosString("Senha: ", "Digite o senha do novo usuário: ");
		String tipoUsuario = (String) JOptionPane.showInputDialog(null, "Informe o tipo do usuario", "Tipo do Usuário",
				JOptionPane.DEFAULT_OPTION, null, ObjetosMenus.MENU_USUARIOS, null);

		Usuario novoUsuario = new Usuario(nomeUsuario, email, senha, tipoUsuario);

		usuarioDAO.newUser(novoUsuario);

	}

	public Usuario login() {
		Usuario utilizador = null;
		// login

		do {

			String emailLogin = tratarVariavel.tratamentosStringNull("Email: ", "Informe seu Email");
			if (emailLogin == null) {
				return utilizador;
			} else {

				String senhaLogin = tratarVariavel.tratamentosString("Senha: ", "Informe sua Senha");

				utilizador = new Usuario(emailLogin, senhaLogin);

				UsuarioService objUserDAO = new UsuarioService();
				utilizador = objUserDAO.userAutentication(utilizador);

				if (utilizador != null) {
					return utilizador;
				} else {

					JOptionPane.showMessageDialog(null,
							"Usuário não encontrado ou senha incorreta. Por favor, tente novamente!");
				}

			}
		} while (utilizador == null);

		return utilizador;

	}

	public void listarUsuarios() {
		ArrayList<Usuario> listaUsuarios = usuarioDAO.showUsers();
		String textoLista = "Lista de usuários: \n";
		for (Usuario userTemp : listaUsuarios) {
			textoLista += "\nNome do usuário: " + userTemp.getNomeUsuario() + "\nEmail: " + userTemp.getEmail()
					+ "\nTipo do usuário: " + userTemp.getTipoUsuario() + "\n";
		}
		JOptionPane.showMessageDialog(null, textoLista);
	}

}
