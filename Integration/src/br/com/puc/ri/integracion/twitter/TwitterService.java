package br.com.puc.ri.integracion.twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.puc.ri.integracion.dto.IntegrationResponse;
import br.com.puc.ri.integracion.dto.IntegrationResponse.IntegrationType;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterService {

	private static TwitterService instance;
	private static Twitter twitter;

	private TwitterService() {
		twitter = new TwitterFactory().getInstance();
	}

	public static TwitterService getInstance() {
		if (instance == null) {
			instance = new TwitterService();
		}
		return instance;
	}

	public List<IntegrationResponse> buscarTweets(String hashTag, int maxTweets) {

		List<IntegrationResponse> responses = new ArrayList<IntegrationResponse>();

		try {
			hashTag = "#" + hashTag;

			long maxID = -1;

			Map<String, RateLimitStatus> rateLimitStatus;

			rateLimitStatus = twitter.getRateLimitStatus("search");

			RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");

			for (int queryNumber = 0; queryNumber < maxTweets; queryNumber++) {
				if (searchTweetsRateLimit.getRemaining() == 0) {
					try {
						Thread.sleep((searchTweetsRateLimit.getSecondsUntilReset() + 2) * 1000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				Query q = new Query(hashTag);
				q.setCount(maxTweets);

				if (maxID != -1) {
					q.setMaxId(maxID - 1);
				}

				QueryResult r = twitter.search(q);
				if (r.getTweets().size() == 0) {
					break;
				}

				for (Status t : r.getTweets()) {
					if (maxID == -1 || t.getId() > maxID) {
						maxID = t.getId();
					}

					String user = t.getUser().getScreenName();
					String msg = cleanText(t.getText());

					IntegrationResponse response = new IntegrationResponse();
					response.setTipo(IntegrationType.TWITTER);
					response.setUsuario(user);
					response.setMsg(msg);

					String tags = "";
					HashtagEntity[] hashtags = t.getHashtagEntities();
					for (HashtagEntity tag : hashtags) {
						tags += tag.getText();
					}
					response.setTag(tags);

					responses.add(response);
				}

				searchTweetsRateLimit = r.getRateLimitStatus();
			}

		} catch (TwitterException e1) {
			e1.printStackTrace();
		}
		return responses;

	}

	public static String cleanText(String text) {
		text = text.replace("\n", "\\n");
		text = text.replace("\t", "\\t");

		return text;
	}

}
