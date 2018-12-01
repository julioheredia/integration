package br.com.puc.ri.integracion.test;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.internal.org.json.JSONObject;

public class IntegarcionFacebookTeste {

	public static void main(String[] args) {

		try {
			Facebook facebook = new FacebookFactory().getInstance();
			System.out.println(facebook.getAuthorization().toString());

			ResponseList<JSONObject> results = facebook.search("%23morocco");
			System.out.println(results);
			for (JSONObject result : results) {
				System.out.println(result);
			}
			//
			// results = facebook.search("orange", new Reading().until("yesterday"));
			// System.out.println(results);
			// for (JSONObject result : results) {
			// System.out.println(result);
			// }

		} catch (FacebookException e) {
			e.printStackTrace();
		}

	}

}
