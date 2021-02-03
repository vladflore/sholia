package tech.vladflore.sholia.config;

import java.util.Collections;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfig {

	@Value("${app.title}")
	private String appTitle;

	@Value("${app.version}")
	private String appVersion;

	@Value("${app.description}")
	private String appDescription;

	@Value("${app.license}")
	private String appLicense;

	@Value("${app.licenseUrl}")
	private String appLicenseUrl;

	@Value("${app.termsOfServiceUrl}")
	private String appTermsOfserviceUrl;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("tech.vladflore.sholia"))
				.paths(PathSelectors.ant("/api/v1/*")).build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(appTitle, appDescription, appVersion, appTermsOfserviceUrl,
				new Contact("Vlad Flore", "http://vladflore.tech", "flore.vlad@gmail.com"), appLicense, appLicenseUrl,
				Collections.emptyList());
	}

}
