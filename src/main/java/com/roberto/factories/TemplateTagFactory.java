package com.roberto.factories;

import com.roberto.enums.TemplateTagType;
import com.roberto.service.templates.*;

public class TemplateTagFactory {

	public TagTemplateBase getTemplate(Object body, TemplateTagType templateType) {
		
		switch (templateType) {
		case ZplTag:
			return new ZplTag(body);
		default:
			return null;
		}
	}
}
