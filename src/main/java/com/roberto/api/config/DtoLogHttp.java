package com.roberto.api.config;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoLogHttp implements Serializable {

	private static final long serialVersionUID = 5155947629870151915L;

	@JsonProperty("latency_seconds")
	private Double latencySeconds;

	@JsonProperty("path")
	private String path;

	@JsonProperty("request_body")
	private String requestBody;

	@JsonProperty("request_header")
	private String requestHeader;

	@JsonProperty("request_method")
	private String requestMethod;

	@JsonProperty("response_body")
	private String responseBody;

	@JsonProperty("response_header")
	private String responseHeader;

	@JsonProperty("request_size")
	private Integer requestSize;

	@JsonProperty("status_code")
	private Integer statusCode;

	@JsonProperty("url")
	private String url;

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}
}
