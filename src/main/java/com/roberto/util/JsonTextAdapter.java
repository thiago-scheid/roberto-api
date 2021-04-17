package com.roberto.util;

import java.io.IOException;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JsonTextAdapter extends TypeAdapter<String> {

	@Override
	public void write(JsonWriter out, String str) throws IOException {
		out.jsonValue(str);
	}

	@Override
	public String read(JsonReader in) throws IOException {
		return new JsonParser().parse(in).toString();
	}
}