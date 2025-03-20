package br.com.ouvidoria.atendimento.util;

import javax.swing.JOptionPane;

public class Tratamentos {

	public String tratamentosString(String title, String message) {
		String variavelTratar;
		while (true) {
			variavelTratar = JOptionPane.showInputDialog(null, title, message, 1);
			if (variavelTratar == null || variavelTratar.isBlank()) {
				JOptionPane.showMessageDialog(null, "Digite uma informação válida!");
			} else {
				break;
			}

		}
		return variavelTratar;
	}

	public String tratamentosStringNull(String title, String message) {
		String variavelTratar;
		while (true) {
			variavelTratar = JOptionPane.showInputDialog(null, title, message, 1);
			if (variavelTratar == null) {
				return variavelTratar;
			} else if (variavelTratar.isBlank()) {
				JOptionPane.showMessageDialog(null, "Digite uma informação válida!");
			} else {
				break;
			}

		}
		return variavelTratar;
	}
	
	public int validarProtocolo() {
		String informarProtocolo = null;
		int informarProtocoloInt = 0;

		while (informarProtocolo == null) {
			try {

				informarProtocolo = JOptionPane.showInputDialog("Informe o protocolo da manifestação: ");
				if (informarProtocolo == null) {
					informarProtocoloInt = -1;
					return informarProtocoloInt;
				} else if (informarProtocolo.isBlank()) {
					JOptionPane.showMessageDialog(null, "Informe o protocolo da manifestação!");
					informarProtocolo = null;
				} else if (Integer.parseInt(informarProtocolo) < 0) {
					JOptionPane.showMessageDialog(null, "Informe um protocolo válido!");
					informarProtocolo = null;
				} else {
					informarProtocoloInt = Integer.parseInt(informarProtocolo);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Informe apenas números!");
				informarProtocolo = null;
			}

		}
		return informarProtocoloInt;

	}
	
	public int tratamentoConfirmDialog (String title, String message) {
		int variavelTratar = JOptionPane.showConfirmDialog(null, message,
				title, 0);
		return variavelTratar;
	}
}
