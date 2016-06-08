package com.geekyninja.httpclient;

public class RestClientForFlickr {

	private FlickrHTTPClientImpl client;
	
    /**
     * Initialise instance of {@link RESTClientForEM7}
     * 
     */
    public void init() {
        synchronized (this) {
            /*try {
                this.client = FlickrHTTPClientImpl.getInstance();

            } catch (Exception e) {
            	e.printStackTrace();
            }*/
        }
    }
}
