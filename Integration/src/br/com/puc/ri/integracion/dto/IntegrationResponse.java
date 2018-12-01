package br.com.puc.ri.integracion.dto;

public class IntegrationResponse {

	public enum IntegrationType {
		INSTAGRAM, TWITTER;
	}

	private String usuario;
	private String msg;
	private String tag;
	private String post;
	private String comments;
	private String link;
	private IntegrationType tipo;

	public IntegrationResponse() {
		super();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public IntegrationType getTipo() {
		return tipo;
	}

	public void setTipo(IntegrationType tipo) {
		this.tipo = tipo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "IntegrationResponse [usuario=" + usuario + ", msg=" + msg + ", tag=" + tag + ", post=" + post + ", comments=" + comments + ", link=" + link + ", tipo=" + tipo + "]";
	}

}
