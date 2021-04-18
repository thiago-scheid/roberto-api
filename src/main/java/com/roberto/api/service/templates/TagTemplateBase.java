package com.roberto.api.service.templates;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

public abstract class TagTemplateBase {

	private StringBuilder builder = new StringBuilder();
	private Map<String, String> dict = new HashMap<>();

	protected TagTemplateBase(Object tagBody) {
		dict.put("©", "_C2_A9");
		dict.put("®", "_C2_AE");
		dict.put("™", "_E2_84_A2");
		dict.put("Á", "_C3_81");
		dict.put("É", "_C3_89");
		dict.put("Í", "_C3_8D");
		dict.put("Ó", "_C3_93");
		dict.put("Ú", "_C3_9A");
		dict.put("á", "_C3_A1");
		dict.put("é", "_C3_A9");
		dict.put("í", "_C3_AD");
		dict.put("ó", "_C3_B3");
		dict.put("ú", "_C3_BA");
		dict.put("Â", "_C3_82");
		dict.put("Ê", "_C3_8A");
		dict.put("Ô", "_C3_94");
		dict.put("â", "_C3_A2");
		dict.put("ê", "_C3_AA");
		dict.put("ô", "_C3_B4");
		dict.put("À", "_C3_80");
		dict.put("à", "_C3_A0");
		dict.put("Ü", "_C3_9C");
		dict.put("ü", "_C3_BC");
		dict.put("Ç", "_C3_87");
		dict.put("ç", "_C3_A7");
		dict.put("Ã", "_C3_83");
		dict.put("Õ", "_C3_95");
		dict.put("ã", "_C3_A3");
		dict.put("õ", "_C3_B5");
		dict.put("Ñ", "_C3_91");
		dict.put("ñ", "_C3_B1");
	}

	public byte[] getBytes() {
		return builder.toString().getBytes();
	}

	protected void addLine(String content) {
		this.builder.append(content);
	}

	protected void fillFields(Map<String, String> paramsMap) {
		StringSubstitutor sub = new StringSubstitutor(paramsMap);
		sub.replaceIn(builder);
		fixSpecialSimbols(builder);
	}

	protected void fixSpecialSimbols(StringBuilder buffer) {
		for (int i = 0; i < buffer.length(); i++) {
			String letter = String.valueOf(buffer.charAt(i));
			if (dict.containsKey(letter)) {
				buffer.replace(i, i + 1, dict.get(letter));
			}
		}
	}
}
