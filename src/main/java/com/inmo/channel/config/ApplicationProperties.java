package com.inmo.channel.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.validation.constraints.NotNull;

@Configuration
@Getter
@Setter
@Lazy
public class ApplicationProperties {

	// ****************** Database Connection ****************
	@NotNull
	@Value("${application.datasource.driverClassName}")
	private String driverClassName;

	@NotNull
	@Value("${application.datasource.url}")
	private String url;

	@NotNull
	@Value("${application.datasource.username}")
	private String username;

	@NotNull
	@Value("${application.datasource.password}")
	private String password;

	// ****************** Encryption Database ****************
//	@NotNull
//	@Value("${application.serviceprincipal.clientid}")
//	private String clientId;
//
//	@NotNull
//	@Value("${application.serviceprincipal.clientkey}")
//	private String clientKey;

	// ****************** Swagger Information ****************
	@NotNull
	@Value("${application.swagger.title}")
	private String swaggerTitle;

	@NotNull
	@Value("${application.swagger.description}")
	private String swaggerDescription;

	@NotNull
	@Value("${application.swagger.version}")
	private String swaggerVersion;

}
