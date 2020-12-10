package nl.nickthijssen.restclient;

import nl.nickthijssen.commons.serialization.SerializationProvider;
import nl.nickthijssen.commons.serialization.Serializer;
import nl.nickthijssen.restshared.messages.BaseRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class BaseRestClient {

	Serializer<String> serializer = SerializationProvider.getSerializer();

	public abstract String getServerURL();

	public abstract void setServerURL(String serverURL);

	public <T> T executeQueryGetHTML(String queryGet) {

		// Build the query for the REST service
		final String query = getServerURL() + queryGet;

		// Perform the query
		HttpGet httpGet = new HttpGet(query);

		return executeHTMLRequest(httpGet);
	}

	public <T> T executeQueryGet(String queryGet, Class<T> clazz) {

		// Build the query for the REST service
		final String query = getServerURL() + queryGet;

		// Perform the query
		HttpGet httpGet = new HttpGet(query);

		return executeRequest(httpGet, clazz);
	}

	public <T> T executeQueryPost(BaseRequest request, String queryPost, Class<T> clazz) {

		// Build the query for the REST service
		final String query = getServerURL() + queryPost;

		// Perform the query
		HttpPost httpPost = new HttpPost(query);
		httpPost.addHeader("content-type", "application/json");

		StringEntity params;
		try {
			String json = serializer.serialize(request);
			params = new StringEntity(json);
			httpPost.setEntity(params);
		}
		catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		return executeRequest(httpPost, clazz);
	}

	public <T> T executeQueryPut(BaseRequest request, String queryPut, Class<T> clazz) {

		// Build the query for the REST service
		final String query = getServerURL() + queryPut;

		// Perform the query
		HttpPut httpPut = new HttpPut(query);
		httpPut.addHeader("content-type", "application/json");
		StringEntity params;
		try {
			params = new StringEntity(serializer.serialize(request));
			httpPut.setEntity(params);
		}
		catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		return executeRequest(httpPut, clazz);
	}

	public <T> T executeQueryDelete(String queryDelete, Class<T> clazz) {

		// Build the query for the REST service
		final String query = getServerURL() + queryDelete;

		// Perform the query
		HttpDelete httpDelete = new HttpDelete(query);

		return executeRequest(httpDelete, clazz);
	}

	private <T> T executeHTMLRequest(HttpUriRequest request) {
		try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(
				request);) {
			HttpEntity entity = response.getEntity();
			final String entityString = EntityUtils.toString(entity);
			System.out.println(entityString);
			return (T) entityString;
		}
		catch (IOException e) {
			return null;
		}
	}

	private <T> T executeRequest(HttpUriRequest request, Class<T> clazz) {
		try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response =
				httpClient.execute(
				request);) {
			HttpEntity entity = response.getEntity();
			final String entityString = EntityUtils.toString(entity);
			System.out.println(entityString);
			return serializer.deserialize(entityString, clazz);
		}
		catch (IOException e) {
			return null;
		}
	}
}
