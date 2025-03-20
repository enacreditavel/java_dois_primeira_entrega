package br.com.ouvidoria.atendimento.server;

import javax.swing.JOptionPane;

import br.com.ouvidoria.atendimento.entity.Manifestacao;
import br.com.ouvidoria.atendimento.entity.ObjetosMenus;
import br.com.ouvidoria.atendimento.entity.Usuario;
import br.com.ouvidoria.atendimento.services.ManifestacaoService;

public class Servidor {
	//ArrayList<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
	Tratamentos tratarVariavel = new Tratamentos();
	ManifestacaoService manifestacaoDAO = new ManifestacaoService();

	String adicionarManifestacao(Usuario utilizador) {
		String mensagemRetorno = null;
		String descricaoManifestacao = null;

		String tipoManifestacao = (String) JOptionPane.showInputDialog(null,
				"Escolha o tipo da sua manifestação: \n", "OUVIDORIA ADS", JOptionPane.DEFAULT_OPTION, null,
				ObjetosMenus.MENU_TIPOS_MANIFESTACAO_ADICIONAR, null);
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
		Manifestacao novaManifestacao = new Manifestacao(utilizador.getNomeUsuario(), tipoManifestacao ,
				descricaoManifestacao, utilizador.getEmail());

		manifestacaoDAO.newManifestation(novaManifestacao);
		int protocolo = manifestacaoDAO.protocolReturn(novaManifestacao);
		mensagemRetorno = "Manifestação adicionada com sucesso! Seu protocolo é: " + protocolo;
		
		return mensagemRetorno;
	}

	
	
}
