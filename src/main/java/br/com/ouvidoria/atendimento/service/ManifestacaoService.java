package br.com.ouvidoria.atendimento.service;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.ouvidoria.atendimento.controller.ManifestacaoController;
import br.com.ouvidoria.atendimento.entity.Manifestacao;
import br.com.ouvidoria.atendimento.entity.ObjetosMenus;
import br.com.ouvidoria.atendimento.entity.Usuario;
import br.com.ouvidoria.atendimento.util.Tratamentos;

public class ManifestacaoService{

	// LISTAR MANIFESTAÇÕES PARA QUANDO O USUÁRIO FOR COMUM, OU SEJA, SÓ TEM ACESSO
	// ÀS MANIFESTAÇÕES QUE ELE PRÓPRIO FEZ
	public String listarManifestacoes(Usuario utilizador) {
		ArrayList<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
		String textoLista = "Lista de manifestações: \n";
		String tipoManifestacao = (String) JOptionPane.showInputDialog(null, "Escolha o tipo da manifestação: \n",
				"OUVIDORIA ADS", JOptionPane.DEFAULT_OPTION, null, ObjetosMenus.MENU_TIPOS_MANIFESTACAO_FILTRO, null);

		// CANCELAMENTO DA OPERAÇÃO
		if (tipoManifestacao == null) {
			textoLista = "Operação cancelada pelo usuário";
			return textoLista;

			// LISTA TODAS AS MANIFESTAÇÕES REALIZADAS PELO USUÁRIO
		} else if (tipoManifestacao.equals(ObjetosMenus.MENU_TIPOS_MANIFESTACAO_FILTRO[0])) {
			manifestacoes = manifestacaoDAO.showManifestations(utilizador.getEmail());

			for (Manifestacao manifestacaoTemp : manifestacoes) {
				if ((manifestacaoTemp.getEmailUsuario()).equals(utilizador.getEmail())) {
					textoLista += "\nProtocolo: " + manifestacaoTemp.getProtocolo() + "\nAutor: "
							+ manifestacaoTemp.getNomeUsuario() + "\nDescrição: " + manifestacaoTemp.getDescricao()
							+ "\nTipo: " + manifestacaoTemp.getTipoManifestacao() + "\n";
				}
			}

			// LISTAR AS MANIFESTAÇÕES REALIZADAS PELO USUÁRIO POR FILTRO (RECLAMAÇÃO,
			// DÚVIDA, ELOGIO OU SUGESTÃO)
		} else {
			manifestacoes = manifestacaoDAO.showManifestationsByFilter(utilizador.getEmail(), tipoManifestacao);

			for (Manifestacao manifestacaoTemp : manifestacoes) {
				if ((manifestacaoTemp.getTipoManifestacao()).equals(tipoManifestacao)
						&& (manifestacaoTemp.getEmailUsuario()).equals(utilizador.getEmail())) {
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

	// ArrayList<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
	Tratamentos tratarVariavel = new Tratamentos();
	ManifestacaoController manifestacaoDAO = new ManifestacaoController();

	public String adicionarManifestacao(Usuario utilizador) {
		String mensagemRetorno = null;
		String descricaoManifestacao = null;

		String tipoManifestacao = (String) JOptionPane.showInputDialog(null, "Escolha o tipo da sua manifestação: \n",
				"OUVIDORIA ADS", JOptionPane.DEFAULT_OPTION, null, ObjetosMenus.MENU_TIPOS_MANIFESTACAO_ADICIONAR,
				null);
		if (tipoManifestacao == null) {
			mensagemRetorno = "Operação cancelada pelo usuário!";
			return mensagemRetorno;
		}

		while (descricaoManifestacao == null) {
			descricaoManifestacao = tratarVariavel.tratamentosStringNull("Descrição", "Descreva sua manifestação");
			if (descricaoManifestacao == null) {
				mensagemRetorno = "Operação cancelada pelo usuário!";
				return mensagemRetorno;
			}
		}
		Manifestacao novaManifestacao = new Manifestacao(utilizador.getNomeUsuario(), tipoManifestacao,
				descricaoManifestacao, utilizador.getEmail());

		manifestacaoDAO.newManifestation(novaManifestacao);
		int protocolo = manifestacaoDAO.protocolReturn(novaManifestacao);
		mensagemRetorno = "Manifestação adicionada com sucesso! Seu protocolo é: " + protocolo;

		return mensagemRetorno;
	}

	public boolean pesquisarManifestacaoPorProtocoloComum(int protocoloInformado, Usuario utilizador) {
		ArrayList<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
		Manifestacao manifestacaoPesquisada = null;
		String mensagemRetorno = null;
		if (protocoloInformado == -1) {
			mensagemRetorno = "Operação cancelada pelo usuário!";
			JOptionPane.showMessageDialog(null, mensagemRetorno);
			return false;
		}

		manifestacoes = manifestacaoDAO.searchManifestationsByProtocol(utilizador.getEmail(), protocoloInformado);

		for (Manifestacao manifestacaoTemp : manifestacoes) {
			if (manifestacaoTemp.getProtocolo() == protocoloInformado
					&& manifestacaoTemp.getEmailUsuario().equals(utilizador.getEmail())) {
				manifestacaoPesquisada = manifestacaoTemp;
				break;
			}
		}
		if (manifestacaoPesquisada == null) {
			mensagemRetorno = "Não existem manifestações do usuário com esse protocolo!";
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

	public void excluirManifestaçãoComum(int protocoloInformado, Usuario utilizador) {
		Tratamentos tratamento = new Tratamentos();
		boolean manifestacaoEncontrada = pesquisarManifestacaoPorProtocoloComum(protocoloInformado, utilizador);
		if (manifestacaoEncontrada == true) {
			int confirmacaoExclusao = tratamento.tratamentoConfirmDialog("Confirmação",
					"Deseja realmente excluir essa manifestação?");
			if (confirmacaoExclusao == 0) {
				manifestacaoDAO.deleteManifestation(protocoloInformado);
				JOptionPane.showMessageDialog(null, "Manifestação excluída com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário!");
			}
		}
	}

}
