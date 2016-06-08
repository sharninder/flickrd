package com.geekyninja.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class FlickrHTTPClientImpl {

	private String baseUri = "https://https://api.flickr.com/services/rest/?";

	// private constructor to support singleton
	private FlickrHTTPClientImpl() {
	}

	public static FlickrHTTPClientImpl getInstance(String protocol, String hostname, String port) {
		return new FlickrHTTPClientImpl();
	}

	/**
	 * This method executes get request
	 * 
	 * @param uri
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String executeGet(String endpoint, Map<String, String> headerMap)
			throws ClientProtocolException, IOException {

		String uri = this.baseUri + endpoint;
		String responseObject = null;
		CloseableHttpClient closeableHttpClient = null;
		CloseableHttpResponse closeableHttpResponse = null;
		HttpGet httpGet = null;

		try {
			closeableHttpClient = FlickrHTTPClientFactory.getFactoryInstance().getHttpClient();

			httpGet = new HttpGet(uri);

			// add request header
			if (headerMap != null && !headerMap.isEmpty()) {
				Set<String> headerKeySet = headerMap.keySet();
				for (String headerKey : headerKeySet) {
					httpGet.setHeader(headerKey, headerMap.get(headerKey));
				}
			}

			closeableHttpResponse = closeableHttpClient.execute(httpGet);

			if (closeableHttpResponse != null) {
				if (closeableHttpResponse.getStatusLine() != null)
					/*
					 * logger.info("Response Code: " +
					 * closeableHttpResponse.getStatusLine() .getStatusCode());
					 */

					if (closeableHttpResponse.getEntity() != null
							&& closeableHttpResponse.getEntity().getContent() != null) {
						BufferedReader rd = new BufferedReader(
								new InputStreamReader(closeableHttpResponse.getEntity().getContent()));

						StringBuffer result = new StringBuffer();
						String line = "";
						while ((line = rd.readLine()) != null) {
							result.append(line);
						}

						responseObject = result.toString();
					}
			}

			EntityUtils.consume(closeableHttpResponse.getEntity());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (closeableHttpResponse != null)
				closeableHttpResponse.close();
		}

		return responseObject;
	}

	/**
	 * This method executes get request
	 * 
	 * @param uri
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String[] executeGetWithHttpStatus(String endpoint, Map<String, String> headerMap)
			throws ClientProtocolException, IOException {

		String uri = this.baseUri + endpoint;
		String responseObject = null;
		String responseCode = "";
		CloseableHttpClient closeableHttpClient = null;
		CloseableHttpResponse closeableHttpResponse = null;
		HttpGet httpGet = null;

		try {
			closeableHttpClient = FlickrHTTPClientFactory.getFactoryInstance().getHttpClient();

			httpGet = new HttpGet(uri);

			// add request header
			if (headerMap != null && !headerMap.isEmpty()) {
				Set<String> headerKeySet = headerMap.keySet();
				for (String headerKey : headerKeySet) {
					httpGet.setHeader(headerKey, headerMap.get(headerKey));
				}
			}

			closeableHttpResponse = closeableHttpClient.execute(httpGet);

			if (closeableHttpResponse != null) {
				if (closeableHttpResponse.getStatusLine() != null)

					if (closeableHttpResponse.getEntity() != null
							&& closeableHttpResponse.getEntity().getContent() != null) {
						BufferedReader rd = new BufferedReader(
								new InputStreamReader(closeableHttpResponse.getEntity().getContent()));

						StringBuffer result = new StringBuffer();
						String line = "";
						while ((line = rd.readLine()) != null) {
							result.append(line);
						}

						responseObject = result.toString();
						responseCode = String.valueOf(closeableHttpResponse.getStatusLine().getStatusCode());

					}
			}

			EntityUtils.consume(closeableHttpResponse.getEntity());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (closeableHttpResponse != null)
				closeableHttpResponse.close();
		}

		String[] responseArray = new String[] { responseCode, responseObject };
		return responseArray;
	}

}
