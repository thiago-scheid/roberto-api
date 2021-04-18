package com.roberto.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<HttpLoggingFilter> loggingFilter() {
		final FilterRegistrationBean<HttpLoggingFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new HttpLoggingFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setEnabled(Boolean.TRUE);
		registrationBean.setName("Filter Log");
		registrationBean.setAsyncSupported(Boolean.TRUE);
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
