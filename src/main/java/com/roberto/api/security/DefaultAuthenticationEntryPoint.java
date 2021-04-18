package com.roberto.api.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.google.gson.Gson;

public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final String UNAUTHORIZED_DEVELOPER_MESSAGE = "Unauthorized - make sure the header parameter Authorization is valid";
	private static final String UNAUTHORIZED_USER_MESSAGE = "You are not authorized to perform this operation";
	private static final long UNAUTHORIZED_ERROR_CODE = 30001l;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(new Gson().toJson(
				new MessageDto(UNAUTHORIZED_DEVELOPER_MESSAGE, UNAUTHORIZED_USER_MESSAGE, UNAUTHORIZED_ERROR_CODE)));
	}
}
