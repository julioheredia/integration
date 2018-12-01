package br.com.puc.ri.integracion.test;

import java.math.BigDecimal;

import javax.swing.JFormattedTextField;

import br.com.puc.ri.integracion.controller.ColetaController;
import br.com.puc.ri.integracion.util.DecimalFormattedField;

public class AplicationTest {

	public static void main(String[] args) {
		// ColetaController coletaController = new ColetaController();
		// coletaController.coletaDadosIntegracoes("pantera", 10, null);

		JFormattedTextField porcentagem = new DecimalFormattedField(DecimalFormattedField.PORCENTAGEM);
		porcentagem.setValue(BigDecimal.valueOf(60.56D));
		String p = porcentagem.getText();
		System.out.println(p);
	}
}
