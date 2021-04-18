package com.roberto.api.config;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicates;
import com.roberto.api.annotation.HideApiDocumentation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.*;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${info.build.version}")
	private String version;

	@Bean
	public Docket postsApi() {
		
		return new Docket(DocumentationType.SWAGGER_2).groupName("com.roberto").securitySchemes(Arrays.asList(apiKey()))
				.securityContexts(Collections.singletonList(securityContext())).apiInfo(apiInfo()).select()
				.paths(regex("/.*"))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(not(withMethodAnnotation(HideApiDocumentation.class))).paths(PathSelectors.any()).build();
	}

	private ApiKey apiKey() {
		return new ApiKey("Bearer", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
	}

	private List<SecurityReference> defaultAuth() {
		
		final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[] { authorizationScope };
		return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API Roberto").description("API de impressão").version(version).build();
	}
}
