package br.com.ouvidoria.atendimento;

import javax.swing.JOptionPane;

import br.com.ouvidoria.atendimento.entity.ObjetosMenus;
import br.com.ouvidoria.atendimento.entity.Usuario;
import br.com.ouvidoria.atendimento.service.ManifestacaoService;
import br.com.ouvidoria.atendimento.util.Tratamentos;
import br.com.ouvidoria.atendimento.service.LoginService;

public class AtendimentoApplication {
	static Tratamentos tratamento = new Tratamentos();
	static String selecao;
	static LoginService loginService = new LoginService();
	static Usuario utilizador;
	static ManifestacaoService manifestacaoService = new ManifestacaoService();

	public static void main(String[] args) {
		while (true) {
			utilizador = loginService.login();
			if (utilizador != null) {
				String bemVindoUsuario = "Olá, " + utilizador.getNomeUsuario() + "!\n";

				menuComum(utilizador, bemVindoUsuario);

			} else {
				break;
			}
		}
	}

	static void menuComum(Usuario utilizador, String bemVindoUsuario) {

		do {
			selecao = (String) JOptionPane.showInputDialog(null, bemVindoUsuario + "O que deseja fazer:\n",
					"OUVIDORIA ADS", JOptionPane.DEFAULT_OPTION, null, ObjetosMenus.MENU_INICIAR, null);

			if (selecao == "LISTAR MANIFESTAÇÕES") {
				String mensagemRetorno = manifestacaoService.listarManifestacoes(utilizador);
				JOptionPane.showMessageDialog(null, mensagemRetorno);

			} else if (selecao == "ADICIONAR MANIFESTAÇÃO") {
				String mensagemRetorno = manifestacaoService.adicionarManifestacao(utilizador);
				JOptionPane.showMessageDialog(null, mensagemRetorno);

			} else if (selecao == "PESQUISAR MANIFESTAÇÃO") {
				int protocoloInformado = tratamento.validarProtocolo();
				manifestacaoService.pesquisarManifestacaoPorProtocoloComum(protocoloInformado, utilizador);

			} else if (selecao == "EXCLUIR MANIFESTAÇÃO") {
				int protocoloInformado = tratamento.validarProtocolo();
				manifestacaoService.excluirManifestaçãoComum(protocoloInformado, utilizador);

			} else if (selecao == null) {
				int sair = JOptionPane.showConfirmDialog(null, "Deseja deslogar?");
				if (sair == 0) {
					selecao = "DESLOGAR";
				}
			}

		} while (selecao != "DESLOGAR");
	}

}