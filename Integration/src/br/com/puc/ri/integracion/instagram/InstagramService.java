package br.com.puc.ri.integracion.instagram;

import java.util.ArrayList;
import java.util.List;

import org.instagram4j.DefaultInstagramClient;
import org.instagram4j.InstagramClient;
import org.instagram4j.InstagramException;
import org.instagram4j.Parameter;
import org.instagram4j.Result;
import org.instagram4j.entity.Comment;
import org.instagram4j.entity.Media;

import br.com.puc.ri.integracion.dto.IntegrationResponse;
import br.com.puc.ri.integracion.dto.IntegrationResponse.IntegrationType;

public class InstagramService {

	private static InstagramService instance;
	private static InstagramClient instagram;

	private static String CLIENT_ID = "8663bbc62a6a44218c2a9aeca9cf2a09";
	private static String CLIENT_SECRET = "9d 2ea ac93f8e4f50a447d6da5fdf301c";
	private static String ACCESS_TOKEN = "2271616111.8663bbc.0da55eb408e24d25bbabae46a5641fa9";

	private InstagramService() {

		instagram = new DefaultInstagramClient(CLIENT_ID, CLIENT_SECRET, ACCESS_TOKEN);
		instagram.setSignedHeaderEnabled(true);
		instagram.setClientIps("127.0.0.1");
	}

	public static InstagramService getInstance() {
		if (instance == null) {
			instance = new InstagramService();
		}
		return instance;
	}

	public List<IntegrationResponse> buscarInstagramPosts(String hashTag, int maxTweets) {
		List<IntegrationResponse> responses = new ArrayList<IntegrationResponse>();

		try {

			hashTag = hashTag.toLowerCase();

			Parameter parameter = Parameter.as("count", Integer.valueOf(maxTweets).toString());
			Result<Media[]> resultSearch = instagram.getRecentMediaForTag(hashTag, parameter);

			if (resultSearch.getData() != null) {
				for (Media media : resultSearch.getData()) {

					IntegrationResponse response = new IntegrationResponse();
					response.setTipo(IntegrationType.INSTAGRAM);
					response.setUsuario(media.getUser().getFullName());
					response.setLink(media.getLink());
					response.setMsg(media.getCaption().getText());

					String tags = "";
					for (String tag : media.getTags()) {
						tags += tag;
					}
					response.setTag(tags);

					String comments = "";
					for (Comment comment : media.getComments().getData()) {
						comments += comment;
					}
					response.setComments(comments);

					responses.add(response);
				}
			}

		} catch (InstagramException e) {
			e.printStackTrace();
		}
		return responses;

	}
}
