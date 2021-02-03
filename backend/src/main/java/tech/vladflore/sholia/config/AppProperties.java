package tech.vladflore.sholia.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

	private String title;

	private String version;

	private String description;

	private String license;

	private String licenseUrl;

	private String termsOfServiceUrl;

}
