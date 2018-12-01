package br.com.puc.ri.integracion.test;

import br.com.puc.ri.integracion.twitter.TwitterService;

public class IntegarcionTwitterTeste {

	public static void main(String[] args) {

		TwitterService twitterService = TwitterService.getInstance();
		twitterService.buscarTweets("galo", 1);

	}

}
