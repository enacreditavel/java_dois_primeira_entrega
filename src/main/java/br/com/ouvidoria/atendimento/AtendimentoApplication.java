package br.com.ouvidoria.atendimento;

import javax.swing.JOptionPane;

import br.com.ouvidoria.atendimento.entity.ObjetosMenus;
import br.com.ouvidoria.atendimento.entity.Usuario;
import br.com.ouvidoria.atendimento.server.ServerAdmin;
import br.com.ouvidoria.atendimento.server.ServerComum;
import br.com.ouvidoria.atendimento.server.ServerLogin;
import br.com.ouvidoria.atendimento.server.Tratamentos;

public class AtendimentoApplication {
	static Tratamentos tratamento = new Tratamentos();
	static String selecao;
	static ServerAdmin serverAdmin = new ServerAdmin();
	static ServerLogin serverLogin = new ServerLogin();
	static Usuario utilizador;
	static ServerComum serverComum = new ServerComum();
	
	public static void main(String[] args) {
		while (true) {
			utilizador = serverLogin.login();
			if (utilizador != null) {
				String bemVindoUsuario = "Olá, " + utilizador.getNomeUsuario() + "!\n";

				if (utilizador.getTipoUsuario().equalsIgnoreCase("Admin")) {
					menuAdmin(utilizador, bemVindoUsuario);
				} else if (utilizador.getTipoUsuario().equalsIgnoreCase("Comum")) {
					menuComum(utilizador, bemVindoUsuario);
				}
			} else {
				break;

			}

		}
	}

	private static void menuAdmin(Usuario utilizador, String bemVindoUsuario) {

		do {
			selecao = (String) JOptionPane.showInputDialog(null, bemVindoUsuario + "O que deseja fazer:\n",
					"OUVIDORIA ADS", JOptionPane.DEFAULT_OPTION, null, ObjetosMenus.MENU_INICIAR_ADMIN, null);

			if (selecao == "LISTAR MANIFESTAÇÕES") {
				String mensagemRetorno = serverAdmin.listarManifestacoesAdmin();
				JOptionPane.showMessageDialog(null, mensagemRetorno);

			} else if (selecao == "ADICIONAR MANIFESTAÇÃO") {
				String mensagemRetorno = serverAdmin.adicionarManifestacao(utilizador);
				JOptionPane.showMessageDialog(null, mensagemRetorno);

			} else if (selecao == "PESQUISAR MANIFESTAÇÃO") {
				int protocoloInformado = tratamento.validarProtocolo();
				serverAdmin.pesquisarManifestacaoPorProtocoloAdmin(protocoloInformado);

			} else if (selecao == "EXCLUIR MANIFESTAÇÃO") {
				int protocoloInformado = tratamento.validarProtocolo();
				serverAdmin.excluirManifestaçãoAdmin(protocoloInformado);

			} else if (selecao == "ADICIONAR RÁPIDO") {
				// serverAdmin.adicionarManifestacaoRapido(utilizador);

			} else if (selecao == "ADICIONAR NOVO USUÁRIO") {
				serverLogin.adicionarNovoUsuario();

			} else if (selecao == "LISTAR USUÁRIOS") {
				serverLogin.listarUsuarios();

			} else if (selecao == null) {
				int sair = JOptionPane.showConfirmDialog(null, "Deseja deslogar?");
				if (sair == 0) {
					selecao = "DESLOGAR";
				}
			}

		} while (selecao != "DESLOGAR");
	}

	static void menuComum(Usuario utilizador, String bemVindoUsuario) {

		do {
			selecao = (String) JOptionPane.showInputDialog(null, bemVindoUsuario + "O que deseja fazer:\n",
					"OUVIDORIA ADS", JOptionPane.DEFAULT_OPTION, null, ObjetosMenus.MENU_INICIAR, null);

			if (selecao == "LISTAR MANIFESTAÇÕES") {
				String mensagemRetorno = serverComum.listarManifestacoes(utilizador);
				JOptionPane.showMessageDialog(null, mensagemRetorno);

			} else if (selecao == "ADICIONAR MANIFESTAÇÃO") {
				String mensagemRetorno = serverComum.adicionarManifestacao(utilizador);
				JOptionPane.showMessageDialog(null, mensagemRetorno);

			} else if (selecao == "PESQUISAR MANIFESTAÇÃO") {
				int protocoloInformado = tratamento.validarProtocolo();
				serverComum.pesquisarManifestacaoPorProtocoloComum(protocoloInformado, utilizador);

			} else if (selecao == "EXCLUIR MANIFESTAÇÃO") {
				int protocoloInformado = tratamento.validarProtocolo();
				serverComum.excluirManifestaçãoComum(protocoloInformado, utilizador);

			} else if (selecao == "ADICIONAR RÁPIDO") {
				// serverComum.adicionarManifestacaoRapido(utilizador);

			} else if (selecao == null) {
				int sair = JOptionPane.showConfirmDialog(null, "Deseja deslogar?");
				if (sair == 0) {
					selecao = "DESLOGAR";
				}
			}

		} while (selecao != "DESLOGAR");
	}

}