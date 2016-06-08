package com.geekyninja.flickrd;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.geekyninja.httpclient.RestClientForFlickr;

/**
 * Integration
 *
 */
public class Flickrd {
	
	String apikey;
	String apisecret;
	
	public Flickrd(String apikey, String apisecret) {
		this.apikey = apikey;
		this.apisecret = apisecret;
	}
	
	public static void main(String[] args) {

		// Add your own api key and api secret here
		String apikey = args[0];
		String apisecret = args[1];

		RestClientForFlickr client = new RestClientForFlickr();
		client.init(apikey);
		
		try {
			client.callMethod("flickr.test.echo");
			client.callMethod("flickr.collections.getTree");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
