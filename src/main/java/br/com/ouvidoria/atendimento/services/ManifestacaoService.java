package br.com.ouvidoria.atendimento.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.ouvidoria.atendimento.entity.Manifestacao;

public class ManifestacaoService {
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;

	private Connection conn;
	// private Usuario utilizador;

	public void newManifestation(Manifestacao manifestacao) {

		try {
			sql = "INSERT INTO manifestacoes (nomeUsuario, tipoManifestacao, descricao, emailUsuario ) VALUES (?, ?, ?, ?)";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, manifestacao.getNomeUsuario());
			ps.setObject(2, manifestacao.getTipoManifestacao());
			ps.setString(3, manifestacao.getDescricao());
			ps.setString(4, manifestacao.getEmailUsuario());
			ps.execute();
			ps.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro Code (newUser): " + erro.getErrorCode());
			JOptionPane.showMessageDialog(null,
					"Ocorreu um erro ao adicionar novo usuario ao banco! Erro (newUser): " + erro.getMessage());

		}

	}

	public int protocolReturn(Manifestacao manifestacao) {
		// ArrayList<Manifestacao> listaManifestacoes = new ArrayList<Manifestacao>();
		int protocolo = 0;
		try {

			sql = "SELECT protocolo FROM manifestacoes ORDER BY protocolo DESC LIMIT 1";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				protocolo = rs.getInt("protocolo");
			}
			ps.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "protocolReturn error: " + erro);

		}
		return protocolo;

	}

	public void deleteManifestation(int protocolo) {
		try {
			sql = "DELETE FROM manifestacoes where protocolo = ?";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, protocolo);
			ps.execute();
			ps.close();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "deleteManifestationAdmin error: " + erro);
		}
	}

	public ArrayList<Manifestacao> showManifestationsAdmin() {
		ArrayList<Manifestacao> listaManifestacoes = new ArrayList<Manifestacao>();
		try {
			sql = "SELECT * FROM manifestacoes";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Manifestacao maniTemp = new Manifestacao(rs.getString("nomeUsuario"), rs.getString("tipoManifestacao"),
						rs.getString("descricao"), rs.getString("emailUsuario"), rs.getInt("protocolo"));
				listaManifestacoes.add(maniTemp);

			}
			ps.close();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "showUsers error: " + erro);
		}
		return listaManifestacoes;
	}

	public ArrayList<Manifestacao> showManifestationsByFilterAdmin(String tipoManifestacao) {
		ArrayList<Manifestacao> listaManifestacoes = new ArrayList<Manifestacao>();
		try {
			sql = "SELECT * FROM manifestacoes where tipoManifestacao = ?";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tipoManifestacao);
			rs = ps.executeQuery();
			while (rs.next()) {
				Manifestacao maniTemp = new Manifestacao(rs.getString("nomeUsuario"), rs.getString("tipoManifestacao"),
						rs.getString("descricao"), rs.getString("emailUsuario"), rs.getInt("protocolo"));
				listaManifestacoes.add(maniTemp);

			}
			ps.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "showUsers error: " + erro);
		}
		return listaManifestacoes;
	}

	public ArrayList<Manifestacao> showManifestations(String emailUtilizador) {
		ArrayList<Manifestacao> listaManifestacoes = new ArrayList<Manifestacao>();
		try {
			sql = "SELECT * FROM manifestacoes where emailUsuario = ?";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, emailUtilizador);
			rs = ps.executeQuery();
			while (rs.next()) {
				Manifestacao maniTemp = new Manifestacao(rs.getString("nomeUsuario"), rs.getString("tipoManifestacao"),
						rs.getString("descricao"), rs.getString("emailUsuario"), rs.getInt("protocolo"));
				listaManifestacoes.add(maniTemp);

			}
			ps.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "showUsers error: " + erro);
		}
		return listaManifestacoes;
	}

	public ArrayList<Manifestacao> showManifestationsByFilter(String emailUsuario, String tipoManifestacao) {
		ArrayList<Manifestacao> listaManifestacoes = new ArrayList<Manifestacao>();
		try {
			sql = "SELECT * FROM manifestacoes where tipoManifestacao = ? and emailUsuario = ?";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tipoManifestacao);
			ps.setString(2, emailUsuario);
			rs = ps.executeQuery();
			while (rs.next()) {
				Manifestacao maniTemp = new Manifestacao(rs.getString("nomeUsuario"), rs.getString("tipoManifestacao"),
						rs.getString("descricao"), rs.getString("emailUsuario"), rs.getInt("protocolo"));
				listaManifestacoes.add(maniTemp);

			}
			ps.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "showUsers error: " + erro);
		}
		return listaManifestacoes;
	}

	public ArrayList<Manifestacao> searchManifestationsByProtocolAdmin(int protocolo) {
		ArrayList<Manifestacao> listaManifestacoes = new ArrayList<Manifestacao>();
		try {
			sql = "SELECT * FROM manifestacoes where protocolo = ?";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, protocolo);
			rs = ps.executeQuery();
			while (rs.next()) {
				Manifestacao maniTemp = new Manifestacao(rs.getString("nomeUsuario"), rs.getString("tipoManifestacao"),
						rs.getString("descricao"), rs.getString("emailUsuario"), rs.getInt("protocolo"));
				listaManifestacoes.add(maniTemp);

			}
			ps.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "showUsers error: " + erro);
		}
		return listaManifestacoes;
	}

	public ArrayList<Manifestacao> searchManifestationsByProtocol(String emailUsuario, int protocolo) {
		ArrayList<Manifestacao> listaManifestacoes = new ArrayList<Manifestacao>();
		try {
			sql = "SELECT * FROM manifestacoes where emailUsuario = ? and protocolo = ?";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, emailUsuario);
			ps.setInt(2, protocolo);
			rs = ps.executeQuery();
			while (rs.next()) {
				Manifestacao maniTemp = new Manifestacao(rs.getString("nomeUsuario"), rs.getString("tipoManifestacao"),
						rs.getString("descricao"), rs.getString("emailUsuario"), rs.getInt("protocolo"));
				listaManifestacoes.add(maniTemp);

			}
			ps.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "showUsers error: " + erro);
		}
		return listaManifestacoes;
	}

}
