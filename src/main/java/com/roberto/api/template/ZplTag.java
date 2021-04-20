package com.roberto.api.template;

public class ZplTag extends TagTemplateBase {

	public ZplTag(Object tagBody) {
		
		String zpl = (String) tagBody;

		addLine(zpl);
	}
}