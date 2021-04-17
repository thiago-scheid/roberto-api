package com.roberto.service.templates;

public class ZplTag extends TagTemplateBase {

	public ZplTag(Object tagBody) {
		super(tagBody);

		String zpl = (String) tagBody;

		addLine(zpl);
	}
}