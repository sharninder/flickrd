package com.geekyninja.flickr.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestEcho {

	@SerializedName("method")
	@Expose
	private Method method;
	
	@SerializedName("api_key")
	@Expose
	private ApiKey apiKey;
	
	@SerializedName("format")
	@Expose
	private Format format;
	
	@SerializedName("nojsoncallback")
	@Expose
	private Nojsoncallback nojsoncallback;
	
	@SerializedName("auth_token")
	@Expose
	private AuthToken authToken;
	
	@SerializedName("api_sig")
	@Expose
	private ApiSig apiSig;
	
	@SerializedName("stat")
	@Expose
	private String stat;

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public ApiKey getApiKey() {
		return apiKey;
	}

	public void setApiKey(ApiKey apiKey) {
		this.apiKey = apiKey;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public Nojsoncallback getNojsoncallback() {
		return nojsoncallback;
	}

	public void setNojsoncallback(Nojsoncallback nojsoncallback) {
		this.nojsoncallback = nojsoncallback;
	}

	public AuthToken getAuthToken() {
		return authToken;
	}

	public void setAuthToken(AuthToken authToken) {
		this.authToken = authToken;
	}

	public ApiSig getApiSig() {
		return apiSig;
	}

	public void setApiSig(ApiSig apiSig) {
		this.apiSig = apiSig;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	
	
}
