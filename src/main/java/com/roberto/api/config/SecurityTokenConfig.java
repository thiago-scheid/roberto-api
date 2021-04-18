package com.roberto.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.beans.factory.annotation.Value;
import com.roberto.api.security.DefaultAuthenticationEntryPoint;
import com.roberto.api.security.JwtTokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtConfig jwtConfig;
	
	@Value("${security.enable-csrf}")
    private boolean csrfEnabled;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		
		if (!csrfEnabled) {
			
		    http
			.csrf()
			.disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(new DefaultAuthenticationEntryPoint())
			.and()
			.authorizeRequests()
			.antMatchers("/actuator/health", 
					     "/actuator/info", 
					     "/swagger-resources/**", 
					     "/v2/api-docs/**",
						 "/csrf/**", 
						 "/webjars/**", 
						 "/swagger-ui.html", 
						 "/v3/api-docs/**", 
						 "/swagger-ui/**")
			.permitAll().anyRequest().authenticated();

			http.addFilter(new JwtTokenAuthenticationFilter(authenticationManager(), jwtConfig));
		}		
	}

	@Bean
	public JwtConfig jwtConfig() {
		return new JwtConfig();
	}
}
