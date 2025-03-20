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

}
