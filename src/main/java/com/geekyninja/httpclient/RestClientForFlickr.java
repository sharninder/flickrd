package com.geekyninja.httpclient;

public class RestClientForFlickr {

	private FlickrHTTPClientImpl client;
	
    /**
     * Initialise instance of {@link RESTClientForEM7}
     * 
     */
    public void init() {
        synchronized (this) {
            try {
                // default
                String port = "443";
                String hostname = "";
                
                this.client = FlickrHTTPClientImpl.getInstance("https",
                            hostname, port);

            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
}
