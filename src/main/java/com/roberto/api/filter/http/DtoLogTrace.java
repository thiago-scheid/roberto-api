package com.roberto.api.filter.http;

import java.io.Serializable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoLogTrace implements Serializable {

	private static final long serialVersionUID = 2382704820244999384L;

	private String id;

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}
}
