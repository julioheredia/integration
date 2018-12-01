package br.com.puc.ri.integracion.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.puc.ri.integracion.dto.IndicadorQualidade;
import br.com.puc.ri.integracion.dto.IntegrationResponse;
import br.com.puc.ri.integracion.dto.RankingWord;
import br.com.puc.ri.integracion.dto.TagBroken;
import br.com.puc.ri.integracion.dto.TagBrokenTotal;
import br.com.puc.ri.integracion.instagram.InstagramService;
import br.com.puc.ri.integracion.twitter.TwitterService;
import br.com.puc.ri.integracion.util.WordsUtil;

public class ColetaController {

	private List<RankingWord> rankingWords;
	private List<IndicadorQualidade> indicadores;

	public TagBrokenTotal coletaDadosIntegracoes(String hashtag, Integer iteracoes, String tipoResultado) {

		List<IntegrationResponse> responses = new ArrayList<IntegrationResponse>();
		responses.addAll(InstagramService.getInstance().buscarInstagramPosts(WordsUtil.tratarTag(hashtag), iteracoes));
		responses.addAll(TwitterService.getInstance().buscarTweets(WordsUtil.tratarTag(hashtag), iteracoes));

		boolean todos = true;
		boolean soBons = false;
		boolean soRuins = false;

		if (tipoResultado.equals("bons")) {
			todos = false;
			soBons = true;
		} else if (tipoResultado.equals("ruins")) {
			todos = false;
			soRuins = true;
		}

		List<TagBroken> tagBrokens = new ArrayList<TagBroken>();

		rankingWords = new ArrayList<RankingWord>();
		indicadores = new ArrayList<IndicadorQualidade>();
		initIndicador();

		TagBrokenTotal tagBrokenTotal = new TagBrokenTotal();

		int numMenssagens = 0;
		int numTotalWords = 0;
		int numTotalBadwords = 0;

		int quantidadeMsgBoas = 0;
		int quantidadeMsgRuins = 0;

		for (IntegrationResponse response : responses) {
			TagBroken tagBroken = new TagBroken(response);

			String menssagem = WordsUtil.retiraStopwords(response.getMsg(), WordsUtil.STOPWORDS_PORTUGUES);
			menssagem = WordsUtil.retiraStopwords(response.getMsg(), WordsUtil.STOPWORDS_ENGLISH);
			tagBroken.setMenssagem(menssagem);

			int numWords = WordsUtil.amountWords(menssagem);
			int numBadwords = WordsUtil.quantidadeBadword(menssagem, WordsUtil.BADWORD_PORTUGUES);
			numBadwords += WordsUtil.quantidadeBadword(menssagem, WordsUtil.BADWORD_ENGLISH);

			boolean isMsgRuim = false;
			if (numBadwords > 0) {
				isMsgRuim = true;
			}

			boolean executa = false;
			if (todos) {
				executa = true;
				numMenssagens++;
				if (isMsgRuim) {
					quantidadeMsgRuins++;
				} else {
					quantidadeMsgBoas++;
				}

			} else if (soBons && !isMsgRuim) {
				executa = true;
				quantidadeMsgBoas++;
				numMenssagens++;

			} else if (soRuins && isMsgRuim) {
				executa = true;
				quantidadeMsgRuins++;
				numMenssagens++;
			}

			if (executa) {
				numTotalWords += numWords;
				numTotalBadwords += numBadwords;

				rankingPalavrasMaisFrequentes(menssagem);

				indicadorQualidade(isMsgRuim, todos, soBons, soRuins);

				tagBrokens.add(tagBroken);
			}
		}

		tagBrokenTotal.setTagBrokens(tagBrokens);
		tagBrokenTotal.setNumTotalMenssagens(numMenssagens);
		tagBrokenTotal.setNumTotalWords(numTotalWords);
		tagBrokenTotal.setNumTotalBadwords(numTotalBadwords);

		Collections.sort(rankingWords, RankingWord.RankingWordComparator);

		List<IndicadorQualidade> indicadoresMsg = indicadorQualidadeMsg(quantidadeMsgBoas, quantidadeMsgRuins);
		for (IndicadorQualidade indicador : indicadoresMsg) {
			indicador.setPorcentagem(calculaPorcentagem(indicador.getQuantidade(), numMenssagens));
		}
		tagBrokenTotal.setIndicadoresMsg(indicadoresMsg);

		for (IndicadorQualidade indicador : indicadores) {
			indicador.setPorcentagem(calculaPorcentagem(indicador.getQuantidade(), numMenssagens));
		}
		tagBrokenTotal.setIndicadores(indicadores);

		tagBrokenTotal.setRakings(rankingWords);

		return tagBrokenTotal;

	}

	private void rankingPalavrasMaisFrequentes(String menssagem) {

		String[] sentSplit = menssagem.split(" ");

		for (String palavra : sentSplit) {

			String word = WordsUtil.validaStopwords(palavra);
			if (StringUtils.isNotBlank(word)) {
				RankingWord ranking = new RankingWord(word);

				if (rankingWords.contains(ranking)) {
					int index = rankingWords.indexOf(ranking);
					RankingWord rankingOld = rankingWords.get(index);
					int count = rankingOld.getCount();
					count++;
					rankingOld.setCount(count);
					rankingWords.set(index, rankingOld);

				} else {

					ranking.setCount(1);
					rankingWords.add(ranking);
				}
			}
		}
	}

	private BigDecimal calculaPorcentagem(int x, int y) {
		if (y > 0) {
			Float f = Float.valueOf(x) / Float.valueOf(y);
			f = f * 100F;
			return BigDecimal.valueOf(f);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private List<IndicadorQualidade> indicadorQualidadeMsg(int quantidadeMsgBoas, int quantidadeMsgRuins) {

		List<IndicadorQualidade> indicadores = new ArrayList<IndicadorQualidade>();
		IndicadorQualidade bom = new IndicadorQualidade();
		bom.setIndicador("Bom");
		bom.setQuantidade(quantidadeMsgBoas);
		indicadores.add(bom);

		IndicadorQualidade ruim = new IndicadorQualidade();
		ruim.setIndicador("Ruim");
		ruim.setQuantidade(quantidadeMsgRuins);
		indicadores.add(ruim);

		return indicadores;
	}

	private void indicadorQualidade(boolean isMsgRuim, boolean todos, boolean soBons, boolean soRuins) {
		String indicador = null;
		if (isMsgRuim) {
			indicador = "Ruim";
		} else {
			indicador = "Bom";
		}

		IndicadorQualidade indicadorQualidade = new IndicadorQualidade();
		indicadorQualidade.setIndicador(indicador);

		if (indicadores.contains(indicadorQualidade)) {
			int index = indicadores.indexOf(indicadorQualidade);
			IndicadorQualidade indicadorOld = indicadores.get(index);
			int count = indicadorOld.getQuantidade();
			count++;
			indicadorOld.setQuantidade(count);
			indicadores.set(index, indicadorOld);
		}
	}

	private void initIndicador() {

		IndicadorQualidade bom = new IndicadorQualidade();
		bom.setIndicador("Bom");
		bom.setQuantidade(0);
		indicadores.add(bom);

		IndicadorQualidade ruim = new IndicadorQualidade();
		ruim.setIndicador("Ruim");
		ruim.setQuantidade(0);
		indicadores.add(ruim);

	}

}
