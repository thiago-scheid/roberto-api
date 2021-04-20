package com.roberto.api.factories;

import com.roberto.api.enums.TemplateTagType;
import com.roberto.api.template.TagTemplateBase;
import com.roberto.api.template.ZplTag;

public class TemplateTagFactory {

	public TagTemplateBase getTemplate(Object body, TemplateTagType templateType) {

		if (templateType.equals(TemplateTagType.ZPLTAG)) {
			return new ZplTag(body);
		}

		return null;
	}
}
