package br.com.ouvidoria.atendimento.server;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.ouvidoria.atendimento.entity.Manifestacao;
import br.com.ouvidoria.atendimento.entity.ObjetosMenus;
import br.com.ouvidoria.atendimento.entity.Usuario;

public class ServerAdmin extends Servidor {

		public String listarManifestacoesAdmin() {
		ArrayList<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
		String tipoManifestacao = (String) JOptionPane.showInputDialog(null, "Escolha o tipo da manifestação: \n",
				"OUVIDORIA ADS", JOptionPane.DEFAULT_OPTION, null, ObjetosMenus.MENU_TIPOS_MANIFESTACAO_FILTRO, null);
		String textoLista = "Lista de manifestações: \n";

		// CANCELAMENTO DA OPERAÇÃO
		if (tipoManifestacao == null) {
			textoLista = "Operação cancelada pelo usuário";
			return textoLista;

			// LISTA TODAS AS MANIFESTAÇÕES REALIZADAS PELO USUÁRIO
		} else if (tipoManifestacao.equals(ObjetosMenus.MENU_TIPOS_MANIFESTACAO_FILTRO[0])) {
			manifestacoes = manifestacaoDAO.showManifestationsAdmin();

			for (Manifestacao manifestacaoTemp : manifestacoes) {
				textoLista += "\nProtocolo: " + manifestacaoTemp.getProtocolo() + "\nAutor: "
						+ manifestacaoTemp.getNomeUsuario() + "\nDescrição: " + manifestacaoTemp.getDescricao()
						+ "\nTipo: " + manifestacaoTemp.getTipoManifestacao() + "\n";
			}

			// LISTAR AS MANIFESTAÇÕES REALIZADAS PELO USUÁRIO POR FILTRO (RECLAMAÇÃO,
			// DÚVIDA, ELOGIO OU SUGESTÃO)
		} else {
			manifestacoes = manifestacaoDAO.showManifestationsByFilterAdmin(tipoManifestacao);
			for (Manifestacao manifestacaoTemp : manifestacoes) {
				if ((manifestacaoTemp.getTipoManifestacao()).equals(tipoManifestacao)) {
					textoLista += "\nProtocolo: " + manifestacaoTemp.getProtocolo() + "\nAutor: "
							+ manifestacaoTemp.getNomeUsuario() + "\nDescrição: " + manifestacaoTemp.getDescricao()
							+ "\nTipo: " + manifestacaoTemp.getTipoManifestacao() + "\n";
				}
			}
		}

		// TRATAMENTO PARA CASO NÃO HAJA MANIFESTAÇÕES DO TIPO SELECIONADO
		if (textoLista == "Lista de manifestações: \n") {
			textoLista = "Não existem manifestações feitas pelo usuário!";
			return textoLista;
		}
		return textoLista;

	}

	public String adicionarManifestacao(Usuario utilizador) {
		String mensagemRetorno = new Servidor().adicionarManifestacao(utilizador);
		return mensagemRetorno;
	}

	public boolean pesquisarManifestacaoPorProtocoloAdmin(int protocoloInformado) {
		ArrayList<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
		Manifestacao manifestacaoPesquisada = null;
		String mensagemRetorno = null;
		if (protocoloInformado == -1) {
			mensagemRetorno = "Operação cancelada pelo usuário!";
			JOptionPane.showMessageDialog(null, mensagemRetorno);
			return false;
		}

		manifestacoes = manifestacaoDAO.searchManifestationsByProtocolAdmin(protocoloInformado);

		for (Manifestacao manifestacaoTemp : manifestacoes) {
			if (manifestacaoTemp.getProtocolo() == protocoloInformado) {
				manifestacaoPesquisada = manifestacaoTemp;
				break;
			}
		}
		if (manifestacaoPesquisada == null) {
			mensagemRetorno = "Não existem manifestações com esse protocolo!";
			JOptionPane.showMessageDialog(null, mensagemRetorno);
			return false;
		} else {

			mensagemRetorno = "Manifestação encontrada: \nProtocolo: " + manifestacaoPesquisada.getProtocolo()
					+ "\nAutor: " + manifestacaoPesquisada.getNomeUsuario() + "\nDescrição: "
					+ manifestacaoPesquisada.getDescricao() + "\nTipo: " + manifestacaoPesquisada.getTipoManifestacao()
					+ "\n";
			JOptionPane.showMessageDialog(null, mensagemRetorno);

		}
		return true;
	}

	public void excluirManifestaçãoAdmin(int protocoloInformado) {
		boolean manifestacaoEncontrada = pesquisarManifestacaoPorProtocoloAdmin(protocoloInformado);
		if (manifestacaoEncontrada == true) {
			int confirmacaoExclusao = tratarVariavel.tratamentoConfirmDialog("Confirmação",
					"Deseja realmente excluir essa manifestação?");
			if (confirmacaoExclusao == 0) {
				manifestacaoDAO.deleteManifestation(protocoloInformado);
				JOptionPane.showMessageDialog(null, "Manifestação excluída com sucesso");
			} else {
				JOptionPane.showMessageDialog(null,"Operação cancelada pelo usuário!");
			}
		}

	}

	/*
	 * public void alterarManifestacaoAdmin(long protocoloInformado) { String
	 * mensagemRetorno = null; Manifestacao manifestacaoPesquisada = null;
	 * 
	 * boolean manifestacaoEncontrada =
	 * pesquisarManifestacaoPorProtocoloAdmin(protocoloInformado); if
	 * (manifestacaoEncontrada == true) { int confirmacaoExclusao =
	 * JOptionPane.showConfirmDialog(null,
	 * "Deseja realmente excluir essa manifestação?", "Confirmação", 0); if
	 * (confirmacaoExclusao == 0) { for (Manifestacao manifestacaoTemp :
	 * manifestacoes) { if (manifestacaoTemp.getProtocolo() == protocoloInformado) {
	 * manifestacaoPesquisada = manifestacaoTemp; break; } } } } }
	 */

	/*
	 * public void adicionarManifestacaoRapido(Usuario utilizador) { Manifestacao
	 * novaReclamacao = new Manifestacao(utilizador.getNomeUsuario(),
	 * utilizador.getEmail(), "Reclamação 1", TipoManifestacao.RECLAMACAO);
	 * protocolo++; manifestacoes.add(novaReclamacao); Manifestacao novaDuvida = new
	 * Manifestacao(utilizador.getNomeUsuario(), utilizador.getEmail(), "Dúvida 1",
	 * protocolo, TipoManifestacao.DUVIDA); protocolo++;
	 * manifestacoes.add(novaDuvida); Manifestacao novaElogio = new
	 * Manifestacao(utilizador.getNomeUsuario(), utilizador.getEmail(), "Elogio 1",
	 * protocolo, TipoManifestacao.ELOGIO); protocolo++;
	 * manifestacoes.add(novaElogio); Manifestacao novaSugestao = new
	 * Manifestacao(utilizador.getNomeUsuario(), utilizador.getEmail(),
	 * "Sugestão 1", protocolo, TipoManifestacao.SUGESTAO); protocolo++;
	 * manifestacoes.add(novaSugestao); }
	 */

}
