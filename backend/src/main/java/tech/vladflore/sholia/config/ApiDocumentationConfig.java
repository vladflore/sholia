package tech.vladflore.sholia.config;

import java.util.Collections;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfig {

	private final AppProperties appProperties;

	public ApiDocumentationConfig(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("tech.vladflore.sholia"))
				.paths(PathSelectors.ant("/api/v1/*")).build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(appProperties.getTitle(), appProperties.getDescription(), appProperties.getVersion(),
				appProperties.getTermsOfServiceUrl(),
				new Contact("Vlad Flore", "http://vladflore.tech", "flore.vlad@gmail.com"), appProperties.getLicense(),
				appProperties.getLicenseUrl(), Collections.emptyList());
	}

}
