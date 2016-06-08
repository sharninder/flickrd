package com.geekyninja.httpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class RestClientForFlickr {

	private FlickrHTTPClientImpl client;
	
	
    /**
     * Initialise instance of {@link RESTClientForEM7}
     * 
     */
    public void init(String apikey) {
        synchronized (this) {
            try {
                this.client = FlickrHTTPClientImpl.getInstance(apikey);

            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
    
    public void callMethod(String method) throws ClientProtocolException, IOException {
    	System.out.println(client.executeGet(method));
    }
}
