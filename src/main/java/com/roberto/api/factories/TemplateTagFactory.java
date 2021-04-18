package com.roberto.api.factories;

import com.roberto.api.enums.TemplateTagType;
import com.roberto.api.service.templates.*;

public class TemplateTagFactory {

	public TagTemplateBase getTemplate(Object body, TemplateTagType templateType) {

		if (templateType.equals(TemplateTagType.ZPLTAG)) {
			return new ZplTag(body);
		}

		return null;
	}
}
