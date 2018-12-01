package br.com.puc.ri.integracion.dto;

import java.math.BigDecimal;

public class IndicadorQualidade {
	private String indicador;
	private Integer quantidade;
	private BigDecimal porcentagem;

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((indicador == null) ? 0 : indicador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndicadorQualidade other = (IndicadorQualidade) obj;
		if (indicador == null) {
			if (other.indicador != null)
				return false;
		} else if (!indicador.equals(other.indicador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IndicadorQualidade [indicador=" + indicador + ", quantidade=" + quantidade + ", porcentagem=" + porcentagem + "]";
	}

}
