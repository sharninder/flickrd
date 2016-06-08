package com.geekyninja.flickrd;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.geekyninja.httpclient.FlickrHTTPClientImpl;

/**
 * Integration
 *
 */
public class Flickrd {
	
	public static void main(String[] args) {

		// Add your own api key and api secret here
		String apikey = args[0];
		String apisecret = args[1];

		try {

			System.out.println(
					FlickrHTTPClientImpl.getInstance(apikey).
					executeGet("flickr.test.echo"));

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
