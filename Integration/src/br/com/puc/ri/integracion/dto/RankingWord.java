package br.com.puc.ri.integracion.dto;

import java.util.Comparator;

public class RankingWord implements Comparable<RankingWord> {

	private String word;
	private Integer count;

	public RankingWord(String word) {
		super();
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getCount() {
		if (this.count == null) {
			count = 0;
		}
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public static Comparator<RankingWord> RankingWordComparator = new Comparator<RankingWord>() {
		public int compare(RankingWord word1, RankingWord word2) {
			int count1 = word1.getCount();
			int count2 = word2.getCount();
			return count2 - count1;
		}
	};

	public int compareTo(RankingWord compareWord) {
		int compareQuantity = ((RankingWord) compareWord).getCount();
		return compareQuantity - this.count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		RankingWord other = (RankingWord) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RankingWord [word=" + word + ", count=" + count + "]";
	}

}
