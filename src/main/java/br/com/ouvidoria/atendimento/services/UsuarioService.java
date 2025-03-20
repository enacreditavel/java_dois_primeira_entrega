package br.com.ouvidoria.atendimento.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.ouvidoria.atendimento.entity.Usuario;

public class UsuarioService {
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private Connection conn;
	private Usuario utilizador;

	public void newUser(Usuario usuario) {

		try {
			sql = "INSERT INTO usuarios (nomeUsuario, email, senha, tipoUsuario) VALUES (?, ?, ?, ?)";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getNomeUsuario());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getTipoUsuario());
			ps.execute();
			ps.close();
			JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso!");

		} catch (SQLException erro) {
			if (erro.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "O email " + usuario.getEmail() + " já está sendo utilizado!");

			} else {
				JOptionPane.showMessageDialog(null, "Erro Code (newUser): " + erro.getErrorCode());
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao adicionar novo usuario ao banco! Erro (newUser): " + erro.getMessage());

			}
		}

	}

	public Usuario userAutentication(Usuario usuario) {
		try {
			sql = "select * from usuarios where email = ? and senha = ?";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getSenha());
			rs = ps.executeQuery();
			if (rs.next()) {
				utilizador = new Usuario(rs.getString("nomeUsuario"), rs.getString("email"),
						rs.getString("tipoUsuario"));
				return utilizador;
			} 
			ps.close();
			return utilizador;

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "userAuthentication error: " + erro);
			return null;
		}

	}

	public ArrayList<Usuario> showUsers() {
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			sql = "SELECT * FROM usuarios";
			conn = ConnectionDataBase.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ps.close();
			while (rs.next()) {
				Usuario userTemp = new Usuario(rs.getString("nomeUsuario"), rs.getString("email"),
						rs.getString("tipoUsuario"));
				listaUsuarios.add(userTemp);

			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "showUsers error: " + erro);
		}
		return listaUsuarios;
	}

}
