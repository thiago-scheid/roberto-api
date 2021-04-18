package com.roberto.api.service.templates;

public class ZplTag extends TagTemplateBase {

	public ZplTag(Object tagBody) {
		
		String zpl = (String) tagBody;

		addLine(zpl);
	}
}