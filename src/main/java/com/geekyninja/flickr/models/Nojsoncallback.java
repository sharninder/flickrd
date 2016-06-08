package com.geekyninja.flickr.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nojsoncallback {

	@SerializedName("_content")
	@Expose
	private Integer content;

	/**
	 * 
	 * @return The content
	 */
	public Integer getContent() {
		return content;
	}

	/**
	 * 
	 * @param content
	 *            The _content
	 */
	public void setContent(Integer content) {
		this.content = content;
	}
}
