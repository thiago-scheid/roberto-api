package com.roberto.factories;

import com.roberto.enums.TemplateTagType;
import com.roberto.service.templates.*;

public class TemplateTagFactory {

	public TagTemplateBase getTemplate(Object body, TemplateTagType templateType) {

		if (templateType.equals(TemplateTagType.ZPLTAG)) {
			return new ZplTag(body);
		}

		return null;
	}
}
