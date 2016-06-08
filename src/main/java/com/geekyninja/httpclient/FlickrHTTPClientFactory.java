package com.geekyninja.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

public class FlickrHTTPClientFactory implements FlickrHTTPClientInterface {

	private ConnectionKeepAliveStrategy aliveStrategy = new ConnectionKeepAliveStrategy() {

		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {

			return keepAliveDurationSeconds * 1000;
		}
	};

	private HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {

		public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
			if ((exception instanceof org.apache.http.conn.ConnectTimeoutException) && executionCount <= 10) {
				System.out.println("##Retrying request count = " + executionCount);
				return true;
			}
			return false;
		}
	};

	private static FlickrHTTPClientFactory clientFactory = new FlickrHTTPClientFactory();

	private HttpClientBuilder httpBuilder = HttpClients.custom();

	private FlickrHTTPClientFactory() {

		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(100);
		connectionManager.setDefaultMaxPerRoute(20);

		httpBuilder.setConnectionManager(connectionManager);
		httpBuilder.setKeepAliveStrategy(aliveStrategy);
		httpBuilder.setRetryHandler(requestRetryHandler);
	}

	/**
	 * 
	 * @return ClientFactory
	 */
	public static FlickrHTTPClientFactory getFactoryInstance() {
		return clientFactory;
	}

	/**
	 * 
	 * @return HttpClient
	 */
	public CloseableHttpClient getHttpClient() {

		SSLConnectionSocketFactory sslsf = FlickrSSLConnectionSocketFactoryBuilder.getInstance().getSSLSocket();

		httpBuilder = clientFactory.getConnectionBuilder()
				.setSSLSocketFactory(FlickrSSLConnectionSocketFactoryBuilder.getInstance().getSSLSocket());

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("https", sslsf).build();

		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
				socketFactoryRegistry);
		connectionManager.setMaxTotal(100);
		connectionManager.setDefaultMaxPerRoute(20);

		httpBuilder.setConnectionManager(connectionManager);

		return httpBuilder.build();

	}

	/**
	 * 
	 * @return ClientBuilder
	 */
	private HttpClientBuilder getConnectionBuilder() {
		return httpBuilder;
	}

}
