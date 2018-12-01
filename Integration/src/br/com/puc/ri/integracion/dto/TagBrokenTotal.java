package br.com.puc.ri.integracion.dto;

import java.util.List;

public class TagBrokenTotal {

	private Integer numTotalMenssagens;
	private Integer numTotalWords;
	private Integer numTotalBadwords;
	private List<IndicadorQualidade> indicadores;
	private List<IndicadorQualidade> indicadoresMsg;
	private List<RankingWord> rakings;
	private List<TagBroken> tagBrokens;

	public Integer getNumTotalMenssagens() {
		return numTotalMenssagens;
	}

	public void setNumTotalMenssagens(Integer numTotalMenssagens) {
		this.numTotalMenssagens = numTotalMenssagens;
	}

	public Integer getNumTotalWords() {
		return numTotalWords;
	}

	public void setNumTotalWords(Integer numTotalWords) {
		this.numTotalWords = numTotalWords;
	}

	public Integer getNumTotalBadwords() {
		return numTotalBadwords;
	}

	public void setNumTotalBadwords(Integer numTotalBadwords) {
		this.numTotalBadwords = numTotalBadwords;
	}

	public List<IndicadorQualidade> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<IndicadorQualidade> indicadores) {
		this.indicadores = indicadores;
	}

	public List<RankingWord> getRakings() {
		return rakings;
	}

	public void setRakings(List<RankingWord> rakings) {
		this.rakings = rakings;
	}

	public List<IndicadorQualidade> getIndicadoresMsg() {
		return indicadoresMsg;
	}

	public void setIndicadoresMsg(List<IndicadorQualidade> indicadoresMsg) {
		this.indicadoresMsg = indicadoresMsg;
	}

	public List<TagBroken> getTagBrokens() {
		return tagBrokens;
	}

	public void setTagBrokens(List<TagBroken> tagBrokens) {
		this.tagBrokens = tagBrokens;
	}

}
