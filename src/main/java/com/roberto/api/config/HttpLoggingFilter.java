package com.roberto.api.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class HttpLoggingFilter implements Filter {

	public static final String X_CLACKS_OVERHEAD = "X-Clacks-Overhead";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader(X_CLACKS_OVERHEAD, "GNU Terry Pratchett");
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		// Do nothing because
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Do nothing because
	}
}