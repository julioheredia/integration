package br.com.puc.ri.integracion.test;

import java.util.List;

import br.com.puc.ri.integracion.dto.IntegrationResponse;
import br.com.puc.ri.integracion.instagram.InstagramService;

public class IntegarcionInstagramTeste {

	public static void main(String[] args) {

		InstagramService instagramService = InstagramService.getInstance();
		List<IntegrationResponse> responses = instagramService.buscarInstagramPosts("pantera", 100);

		for (IntegrationResponse integrationResponse : responses) {
			System.out.println(integrationResponse);
		}

		// String msg = "Tenho um stringBuffer com uma frase dentro. Preciso retirar a primeira palavra da string. Fazendo com que a segunda se torne primeira e a quantidade de palavras seja x-1.";
		// System.out.println(msg);
		// String resp = WordsUtil.retiraStopwords(msg, WordsUtil.STOPWORDS_PORTUGUES);
		// System.out.println(resp);

	}

}
