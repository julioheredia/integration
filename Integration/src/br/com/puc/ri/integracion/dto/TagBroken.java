package br.com.puc.ri.integracion.dto;

public class TagBroken {

	private IntegrationResponse integrationResponse;
	private String menssagem;
	private String user;

	public TagBroken(IntegrationResponse integrationResponse) {
		super();
		this.integrationResponse = integrationResponse;
	}

	public TagBroken() {
		super();
	}

	public String getMenssagem() {
		return menssagem;
	}

	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TagBroken [menssagem=" + menssagem + ", user=" + user + "]";
	}

	public IntegrationResponse getIntegrationResponse() {
		return integrationResponse;
	}

	public void setIntegrationResponse(IntegrationResponse integrationResponse) {
		this.integrationResponse = integrationResponse;
	}

}
